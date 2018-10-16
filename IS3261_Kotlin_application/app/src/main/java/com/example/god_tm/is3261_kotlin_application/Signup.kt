package com.example.god_tm.is3261_kotlin_application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        //define all the edit text for
        var input_username = findViewById<EditText>(R.id.name_input_signup)
        input_username.setGravity(Gravity.CENTER);
        var input_password = findViewById<EditText>(R.id.password_input_signup)
        input_password.setGravity(Gravity.CENTER)
        var input_email    = findViewById<EditText>(R.id.email_input_signup)
        input_email.setGravity(Gravity.CENTER)

    }
}
