package com.example.god_tm.final_application_implementation

import android.provider.BaseColumns

class TableInfo: BaseColumns {

    companion object {

        // users table
        val TABLE_NAME = "users"
        val COLUMN_MAIL = "mail"
        val COLUMN_NAME = "name"
        val COLUMN_POINTS = "points"
        val COLUMN_PROFILE = "profile"

        //Solved table(Relation)
        val RELATION_SOLVED = "isSolved"
        val COLUMN_SOLVED = "solved" // boolean

        // questions table
        val TABLE_NAME_QUESTIONS = "questions"
        val COLUMN_QUESTIONS_ID  = "id"
        val COLUMN_QUESTION = "question"
        val COLUMN_PLACEHOLDER_CODE = "placeholder_code"
        val COLUMN_ANSWER = "answer"
        val COLUMN_QUESTION_POINTS = "points"


    }
}