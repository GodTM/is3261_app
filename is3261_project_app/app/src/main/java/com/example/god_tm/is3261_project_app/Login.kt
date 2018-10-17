package com.example.god_tm.is3261_project_app

import android.content.Intent
import android.os.Bundle
//import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    lateinit var users:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var emailInputLogin = findViewById<EditText>(R.id.email_login_input)
        emailInputLogin.setGravity(Gravity.CENTER) ;
        var passwordInputLogin = findViewById<EditText>(R.id.password_login_input)
        passwordInputLogin.setGravity(Gravity.CENTER)


        // login
        var loginButton = findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {

            if(login()){
                var intent = Intent(this , DefaultActivity::class.java)
                startActivity(intent)

            }
            else{

            }
        }


        // sign Up link
        var signupLink = findViewById<Button>(R.id.signup_link)
        signupLink.setOnClickListener {
            var intent = Intent(this , Signup::class.java)
            startActivity(intent)
        }

        users = DBHelper(this)
    }


    fun login():Boolean{

        var email   = this.email_login_input.text.toString()
        var password= this.password_login_input.text.toString()
        if(Validators.isEmailValid(email)){
            if(users.login(email,password)){
                return true
            }

        }
        return false
    }


    fun resetValues(){



    }

}
