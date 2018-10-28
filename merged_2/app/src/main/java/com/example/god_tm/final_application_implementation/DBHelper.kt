package com.example.god_tm.final_application_implementation

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Users.db"
        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
                        TableInfo.COLUMN_MAIL + " TEXT," +
                        TableInfo.COLUMN_NAME + " TEXT," +
                        TableInfo.COLUMN_POINTS + " INTEGER," +
                        TableInfo.COLUMN_PROFILE + " TEXT,"+
                        "PRIMARY KEY(mail,name)"+
                        ")"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?,
                             oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertPerson(person: DataRecord): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TableInfo.COLUMN_MAIL, person.mail)
        values.put(TableInfo.COLUMN_NAME, person.name)
        values.put(TableInfo.COLUMN_POINTS, person.points)
        values.put(TableInfo.COLUMN_PROFILE, person.profile)
        val newRowId = db.insert(TableInfo.TABLE_NAME,null, values)
        return true
    }

    fun deletePerson(nric: String):Boolean {
        val db = writableDatabase
        val selection = TableInfo.COLUMN_MAIL + " LIKE ?"
        val selectionArgs = arrayOf(nric)

        db.delete(TableInfo.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readPerson(mail: String): ArrayList<DataRecord> {
        val people = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("select * from " +
                    TableInfo.TABLE_NAME + " WHERE " +
                    TableInfo.COLUMN_MAIL + "=" + "'"+ mail + "'", null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }

        var name: String
        var points: Int
        var mail: String
        var profile:String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                mail = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_MAIL))
                name= cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME))
                points=cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_POINTS))
                profile=cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_PROFILE))
                people.add(DataRecord(mail,name,points,profile))
                cursor.moveToNext()
            }
        }
        return people
    }
    fun readAllPeople(): ArrayList<DataRecord> {
        val people = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from "+ TableInfo.TABLE_NAME, null)

        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var mail: String
        var name: String
        var points: Int
        var profile:String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                mail= cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_MAIL))
                name = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME))
                points = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_POINTS))
                profile=cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_PROFILE))
                people.add(DataRecord(mail, name, points,profile))
                cursor.moveToNext()
            }
        }
        return people
    }


}