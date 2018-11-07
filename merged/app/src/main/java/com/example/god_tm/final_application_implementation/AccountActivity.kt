package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        val a = supportActionBar
        a!!.hide()
        supportActionBar!!.hide()
        val user_profile = findViewById<ImageView>(R.id.imageView)
        val user_information = findViewById<TextView>(R.id.textView)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val db = DBHelper(this)
        val user = db.readPerson(acct!!.email!!)
        val point = user[0].points.toInt()
        val profile = acct.photoUrl

        user_information.setText("E-mail : "+acct!!.email+"\n"+
                                "Name : "+acct!!.displayName+"\n"+
                                "Points : "+point+"\n"+
        "Profile : " + profile.toString()+"\n")
        Log.e("profile",profile.toString())
        if(profile.toString() == "null"){
            Toast.makeText(this,"Please check if your Google account has had Google+ profile picture or not.",Toast.LENGTH_LONG).show()
        }
        user_profile.setImageURI(profile)
    }
}
