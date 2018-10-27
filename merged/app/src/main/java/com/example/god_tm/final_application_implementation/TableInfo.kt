package com.example.god_tm.final_application_implementation

import android.provider.BaseColumns

class TableInfo: BaseColumns {

    companion object {
        val TABLE_NAME = "users"
        val COLUMN_MAIL = "mail"
        val COLUMN_NAME = "name"
        val COLUMN_POINTS = "points"
        val COLUMN_PROFILE = "profile"
    }
}