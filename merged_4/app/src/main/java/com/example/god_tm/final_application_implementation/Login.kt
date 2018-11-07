package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.god_tm.final_application_implementation.static_values.Companion.position
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.drive.Drive
import com.google.android.youtube.player.internal.e

class Login : AppCompatActivity() {

    val RC_SIGN_IN = 0
    lateinit var usersDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Drive.SCOPE_FILE)
                .requestEmail()
                .build()
        val mGoogleSignInClient =  GoogleSignIn.getClient(this,gso)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(acct)

        val btn_sign = findViewById<Button>(R.id.login)
        btn_sign.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        usersDBHelper = DBHelper(this)
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val acct = completedTask.getResult(ApiException::class.java)
            updateUI(acct)
            if(usersDBHelper.readPerson(acct.email!!).size == 0){
                addPerson(acct)
            }else{
                Toast.makeText(this,"Logged in",Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            updateUI(null)
        }
    }
    private fun updateUI(acct:GoogleSignInAccount?){
        if(acct != null){
            val i = Intent(this,BaseActivity::class.java)
            startActivity(i)
        }
    }
    fun addPerson(acct:GoogleSignInAccount?) {
        val mail = acct!!.email
        val name = acct.displayName
        val points = "0"
        val profile = acct.photoUrl.toString()
        val urls=""
        val result = usersDBHelper.insertPerson(DataRecord(mail!!,name!!,points,profile,urls))
        Toast.makeText(this,"Added: " + result.toString(), Toast.LENGTH_SHORT).show()
    }

}
