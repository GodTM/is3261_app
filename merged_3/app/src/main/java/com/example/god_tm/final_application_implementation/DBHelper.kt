package com.example.god_tm.final_application_implementation

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Users.db"
        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TableInfo.TABLE_NAME + " (" +
                        TableInfo.COLUMN_MAIL + " TEXT NOT NULL ," +
                        TableInfo.COLUMN_NAME + " TEXT NOT NULL ," +
                        TableInfo.COLUMN_POINTS + " INTEGER NOT NULL ," +
                        TableInfo.COLUMN_PROFILE + " TEXT,"+
                        "PRIMARY KEY(mail,name)"+
                        ")" +
                        " CREATE TABLE"+ TableInfo.TABLE_NAME_QUESTIONS+" ( "+
                        TableInfo.COLUMN_QUESTIONS_ID+" INTEGER PRIMARY KEY , " +
                        TableInfo.COLUMN_QUESTION+" TEXT NOT NULL , "+
                        TableInfo.COLUMN_PLACEHOLDER_CODE+" TEXT NOT NULL, "+
                        TableInfo.COLUMN_ANSWER+" TEXT NOT NULL , " +
                        TableInfo.COLUMN_POINTS+" INTEGER NOT NULL  "+
                        " ) " +

                        " CREATE TABLE "+ TableInfo.RELATION_SOLVED + " ( " +
                        TableInfo.COLUMN_MAIL + " TEXT NOT NULL , "+ TableInfo.COLUMN_NAME+" TEXT NOT NULL , " +
                        TableInfo.COLUMN_QUESTIONS_ID+" INTEGER NOT NULL ,  "+
                        TableInfo.COLUMN_SOLVED + " INT NOT NULL CHECK (status IN('0' , '1')) , " +
                        " FOREIGN KEY ( "+TableInfo.COLUMN_QUESTIONS_ID+ " ) REFERENCES "+TableInfo.TABLE_NAME_QUESTIONS+
                        "( "+ TableInfo.COLUMN_QUESTIONS_ID +" ) , FOREIGN KEY ("+TableInfo.COLUMN_MAIL+ " , "+ TableInfo.COLUMN_NAME + ") REFERENCES "+
                        TableInfo.TABLE_NAME + "("+TableInfo.COLUMN_MAIL +" , "+ TableInfo.COLUMN_NAME +" ) , PRIMARY KEY(mail,id ,solved) " +
                        " ON DELETE CASCADE ON UPDATE CASCADE )"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ TableInfo.RELATION_SOLVED + " , " + TableInfo.TABLE_NAME + " , "+ TableInfo.TABLE_NAME_QUESTIONS+" "
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
        values.put(TableInfo.COLUMN_MAIL, person.mail.toLowerCase())
        values.put(TableInfo.COLUMN_NAME, person.name.toLowerCase())
        values.put(TableInfo.COLUMN_POINTS, person.points)
        values.put(TableInfo.COLUMN_PROFILE, person.profile)
        val newRowId = db.insert(TableInfo.TABLE_NAME,null, values)
        return true
    }

    fun insertQuestion(question:DataRecordQuestions):Boolean{
        val db = writableDatabase
        val values = ContentValues()
        values.put(TableInfo.COLUMN_QUESTIONS_ID, question.id)
        values.put(TableInfo.COLUMN_QUESTION , question.question.toLowerCase())
        values.put(TableInfo.COLUMN_PLACEHOLDER_CODE , question.placeHolderText)
        values.put(TableInfo.COLUMN_ANSWER , question.answer)
        values.put(TableInfo.COLUMN_QUESTION_POINTS , question.points)

        val newRowId = db.insert(TableInfo.TABLE_NAME_QUESTIONS,null , values)
        return true
    }

    fun insertIsSolved(isSolved:isSolved):Boolean{
        // check for errors
        lateinit var db :SQLiteDatabase
        lateinit var values:ContentValues
        try {
             db = writableDatabase
            val values = ContentValues()
        }catch(e:SQLiteException){
            return false
        }
        try{
            values.put(TableInfo.COLUMN_MAIL , isSolved.mail.toLowerCase())
            values.put(TableInfo.COLUMN_NAME , isSolved.name.toLowerCase())
            values.put(TableInfo.COLUMN_QUESTIONS_ID , isSolved.question_id)
            values.put(TableInfo.COLUMN_SOLVED , isSolved.isSolved)
            val newRowId = db.insert(TableInfo.RELATION_SOLVED , null , values)
            return true
        }catch (e:SQLiteException){
            return false
        }
    }



    fun deletePerson(mail: String):Boolean {
        val db = writableDatabase
        val selection = TableInfo.COLUMN_MAIL + " LIKE ?"
        val selectionArgs = arrayOf(mail.toLowerCase())

        db.delete(TableInfo.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun deleteQuestion(id:Int):Boolean{
        val db = writableDatabase
        val selection = TableInfo.COLUMN_QUESTIONS_ID + " LIKE ?"

        /*
        * check for errors in this method , the boolean and the
        * */
        val id_mod = id.toString()
        val selectionArgs = arrayOf(id_mod)

        db.delete(TableInfo.TABLE_NAME_QUESTIONS , selection , selectionArgs)

        return true
    }

    /**
     * No need to add deleteIsSolved - it will be cascaded upon deletion of person
     * */

    fun readPerson(mailParam: String): ArrayList<DataRecord> {
        val people = ArrayList<DataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery("select * from " +
                    TableInfo.TABLE_NAME + " WHERE " +
                    TableInfo.COLUMN_MAIL + "=" + "'"+ mailParam.toLowerCase() + "'", null)
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


    fun readQuestion(idParam:Int):ArrayList<DataRecordQuestions>{
        val questions = ArrayList<DataRecordQuestions>()
        try{
            var db= writableDatabase
            var id:Int
            var question:String
            var placeholderText:String
            var answer:String
            var points:Int
            var cursor:Cursor? = null
            try{
                cursor = db.rawQuery("SELECT * FROM "+TableInfo.TABLE_NAME_QUESTIONS + " WHERE "+ TableInfo.COLUMN_QUESTIONS_ID +" = '"+idParam+"'" , null)
                if(cursor!!.moveToFirst()){
                    while(cursor.isAfterLast==false){
                        id = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_QUESTIONS_ID))
                        question = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_QUESTION))
                        placeholderText= cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_PLACEHOLDER_CODE))
                        answer = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_ANSWER))
                        points = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_QUESTION_POINTS))
                        questions.add(DataRecordQuestions(id ,question , placeholderText , answer , points))
                        cursor.moveToNext()
                    }
                }
                return questions

            }catch (e:SQLiteException) {
                return ArrayList()
            }
        }catch (e:SQLiteException){
            //Log.e()
            return ArrayList()  ;
        }
    }

    fun readIsSolved(mailParam:String, nameParam:String ,idParam:Int , isSolvedParam:Int):ArrayList<isSolved>{
        val isSolvedList = ArrayList<isSolved>() // isSolved int column (last col in isSolved table = 0 or 1) 0 = not solved , 1 = solved
        try{
            val db = writableDatabase
            var cursor:Cursor? = null
            try{
                cursor = db.rawQuery("SELECT * FROM "+TableInfo.RELATION_SOLVED + "WHERE "+TableInfo.COLUMN_MAIL+" = '"+ mailParam.toLowerCase()+
                        "' AND "+TableInfo.COLUMN_NAME+" = '"+ nameParam.toLowerCase() + "' AND "+ TableInfo.COLUMN_QUESTIONS_ID+" = '"+idParam+"' AND "+ TableInfo.COLUMN_SOLVED + " = '"+isSolvedParam+"' ", null)
                var mail:String
                var name:String
                var id:Int
                var isSolved:Int
                if(cursor!!.moveToFirst()){
                    while(!cursor.isAfterLast){
                        mail =      cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_MAIL))
                        name =      cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME))
                        id   =      cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_QUESTIONS_ID))
                        isSolved =  cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_SOLVED))
                        isSolvedList.add(isSolved(mail , name , id , isSolved))
                        cursor.moveToNext()
                    }
                }
                return isSolvedList
            } catch (e:SQLiteException){
                return ArrayList()
            }

        }catch (e:SQLiteException){
            return ArrayList()
        }
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


    fun readAllQuestions():ArrayList<DataRecordQuestions>{
        val questions  = ArrayList<DataRecordQuestions>()
        try {
            val db = writableDatabase
            var cursor:Cursor? = null
            try{
                cursor = db.rawQuery("SELECT * FROM "+TableInfo.TABLE_NAME_QUESTIONS  , null )
                var id:Int
                var question:String
                var placeholderText:String
                var answer:String
                var points:Int
                if(cursor!!.moveToFirst()){
                    while(!cursor.isAfterLast){
                        id = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_QUESTIONS_ID))
                        question = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_QUESTION))
                        placeholderText= cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_PLACEHOLDER_CODE))
                        answer = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_ANSWER))
                        points = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_QUESTION_POINTS))
                        questions.add(DataRecordQuestions(id ,question , placeholderText , answer , points))
                        cursor.moveToNext()
                    }
                }
                return questions
            }catch(e:SQLiteException){
                return ArrayList()
            }
        }catch (e:SQLiteException){
            return ArrayList()
        }
    }



    fun readAllIsSolved():ArrayList<isSolved>{
        val isSolvedList = ArrayList<isSolved>() // isSolved int column (last col in isSolved table = 0 or 1) 0 = not solved , 1 = solved
        try{
            val db = writableDatabase
            var cursor:Cursor? = null
            try{
                cursor = db.rawQuery("SELECT * FROM "+TableInfo.RELATION_SOLVED , null)
                var mail:String
                var name:String
                var id:Int
                var isSolved:Int
                if(cursor!!.moveToFirst()){
                    while(!cursor.isAfterLast){
                        mail =      cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_MAIL))
                        name =      cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME))
                        id   =      cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_QUESTIONS_ID))
                        isSolved =  cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_SOLVED))
                        isSolvedList.add(isSolved(mail , name , id , isSolved))
                        cursor.moveToNext()
                    }
                }
                return isSolvedList
            } catch (e:SQLiteException){
                return ArrayList()
            }

        }catch (e:SQLiteException){
            return ArrayList()
        }
    }


}