package com.example.god_tm.is3261_kotlin_application

import java.util.regex.Matcher
import java.util.regex.Pattern

class Validators {

    fun isEmailValid(email:String):Boolean{

        var regex:String = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

        var inputStr:CharSequence = email

        var pattern:Pattern = Pattern.compile(regex , Pattern.CASE_INSENSITIVE)
        var matcher: Matcher= pattern.matcher(inputStr)

        if(matcher.matches()){
            return true
        }
        return false
    }
}