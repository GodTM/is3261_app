package com.example.god_tm.is3261_project_app

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.io.InputStream

class DBHelper(context: Context): SQLiteOpenHelper(context , DATABASE_NAME , null , DATABASE_VERSION){

    companion object {

        val DATABASE_NAME = "users.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE = " CREATE TABLE "+TableInfo.TABLE_NAME +"  ( "+ TableInfo.USERNAME +" TEXT NOT NULL , "+
                TableInfo.PASSWORD + " TEXT NOT NULL ,  "+ TableInfo.EMAIL+" TEXT PRIMARY KEY) "

        private val DELETE_TABLE = " DROP TABLE IF EXISTS "+ TableInfo.TABLE_NAME ;
    }



    override fun onCreate(db: SQLiteDatabase?) {
        //delete the table if it pre exists
        //db?.execSQL(DELETE_TABLE)
        db?.execSQL(SQL_CREATE_TABLE)

        /*
        var inputStream:InputStream = Resources.openRawResource(R.raw.sample)
        var queries = ""
        try{

            queries = IOUtils
        }catch(e:SQLiteException){
            println(e)
        }
        */


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DELETE_TABLE) ; // delete the table if present before
        onCreate(db) ;
    }




    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        onUpgrade(db , oldVersion , newVersion) ;
    }


    // function for registration
    fun insertUserDetails(userData:UserRecord):Boolean{
        val database = writableDatabase ;
        val values = ContentValues() // read up about contentValues
        values.put(TableInfo.USERNAME , userData.username.trim().toLowerCase())
        values.put(TableInfo.PASSWORD , userData.password.trim())
        values.put(TableInfo.EMAIL , userData.email.trim().toLowerCase())
        try {
            var newRowId = database.insert(TableInfo.TABLE_NAME,
                    null, values)
        }catch (e:SQLiteException){
            print(e)
            return false ;
        }
        return true
    }




    // deleting user details from the
    fun deleteUserDetails(email:String):Boolean{
        var database = writableDatabase
        val selection = TableInfo.EMAIL + " LIKE ?"
        var email_sanitized:String = email.trim().toLowerCase()
        val selectionArgs = arrayOf(email_sanitized)
        try {
            database.delete(TableInfo.TABLE_NAME, selection, selectionArgs)
        }catch(e:SQLiteException){
            print(e)
            return false ;
        }
        return true ;
    }


    // successful login if returns true else is not able to login
    fun login(email:String, password:String):Boolean{
        var database = readableDatabase
        var email_sanitized = email.trim().toLowerCase()
        var password_sanitized = password.trim()
        var cursor: Cursor? = null
        try{
             cursor = database.rawQuery("SELECT COUNT(*) FROM "+TableInfo.TABLE_NAME + " WHERE "+TableInfo.EMAIL + " = "+email_sanitized+
                     " AND "+TableInfo.PASSWORD +" = "+password_sanitized+"",null)
            cursor.moveToFirst()
            var userCount:Int = cursor.getInt(0)
            if(userCount<1){
                return false // launch a new activity // the base page of the application or else
            }
        }catch(e:SQLiteException){
            println(e)
            return false ;
        }
        return true
    }

    /*
    * possible areas of mistakes:
    * 1. What if user tries to update email_id.. we need to check if the new address clashes the any other address
    * */
    fun updateUser(userData: UserRecord):Boolean{
        val values = ContentValues()
        val database = writableDatabase
        var email_sanitized = userData.email.trim().toLowerCase()
        values.put(TableInfo.USERNAME , userData.username.trim().toLowerCase())
        values.put(TableInfo.PASSWORD , userData.password.trim())
        values.put(TableInfo.EMAIL , userData.email.trim().toLowerCase())

        var cursor:Cursor? = null
        try{
            cursor = database.rawQuery("SELECT COUNT(*) FROM "+TableInfo.TABLE_NAME + " WHERE "+TableInfo.EMAIL + " = "+ email_sanitized+" ", null)
            cursor.moveToFirst()
            var emailValidityCheck:Int = cursor.getInt(0)
            if(emailValidityCheck>0){
                return false
            }

            val selection = TableInfo.EMAIL + " LIKE ?"
            val selectionArgs = arrayOf(email_sanitized)
            database.update(TableInfo.TABLE_NAME , values ,selection,selectionArgs)

        }catch(e:SQLiteException){

            print(e)
            return false
        }
        return true
    }






}