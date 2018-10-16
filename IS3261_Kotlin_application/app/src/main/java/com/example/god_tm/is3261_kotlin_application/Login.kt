package com.example.god_tm.is3261_kotlin_application

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var emailInputLogin = findViewById<EditText>(R.id.email_login_input)
        emailInputLogin.setGravity(Gravity.CENTER) ;
        var passwordInputLogin = findViewById<EditText>(R.id.password_login_input)
        passwordInputLogin.setGravity(Gravity.CENTER)

        // sign Up link
        var signupLink = findViewById<Button>(R.id.signup_link)
        signupLink.setOnClickListener {
            var intent = Intent(this , Signup::class.java)
            startActivity(intent)
        }




    }

}
