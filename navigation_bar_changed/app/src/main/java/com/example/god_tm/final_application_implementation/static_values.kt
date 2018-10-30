package com.example.god_tm.final_application_implementation

import android.app.Application

class static_values:Application() {
    companion object {
        var position = -1
    }
    fun get_position():Int{
        return position
    }
    fun set(num:Int){
        position = num
    }
}