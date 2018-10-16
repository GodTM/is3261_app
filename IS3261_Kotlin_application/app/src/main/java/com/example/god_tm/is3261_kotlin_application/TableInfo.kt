package com.example.god_tm.is3261_kotlin_application

import android.provider.BaseColumns

class TableInfo: BaseColumns {

    companion object {

        val TABLE_NAME = "user"
        val USERNAME   = "username"
        val PASSWORD   = "password"
        val EMAIL      = "email"

    }

}