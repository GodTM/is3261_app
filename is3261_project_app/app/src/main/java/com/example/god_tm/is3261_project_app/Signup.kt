package com.example.god_tm.is3261_project_app
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_signup.*

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


    fun addUser():Boolean{

        var username = this.name_input_signup.text.toString()
        var password = this.password_input_signup.text.toString()
        var email    = this.email_input_signup.text.toString()

        if(!Validators.isEmailValid(email)){
            return false
        }

        return true

    }
}
