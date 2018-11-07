package com.example.god_tm.final_application_implementation

import android.app.Application

class static_values:Application() {
    companion object {
        var position = -1
        var login_status = 1
    }
    fun get_position():Int{
        return position
    }
    fun get_status():Int{
        return login_status
    }
    fun set(num:Int){
        position = num
    }
    fun login_set(num:Int){
        login_status=num
    }
}