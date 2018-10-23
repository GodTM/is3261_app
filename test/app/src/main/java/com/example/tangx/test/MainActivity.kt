package com.example.tangx.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.drive.CreateFileActivityOptions
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.MetadataChangeSet

class MainActivity : AppCompatActivity() {

    val RC_SIGN_IN = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Drive.SCOPE_FILE)
                .requestEmail()
                .build()
        val mGoogleSignInClient =  GoogleSignIn.getClient(this,gso)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(acct)

        val btn_sign = findViewById<Button>(R.id.button)
        btn_sign.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
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
        } catch (e: ApiException) {
            updateUI(null)
        }
    }

    private fun updateUI(acct:GoogleSignInAccount?){
        if(acct != null){
            Toast.makeText(this,acct.email+" has already signed",Toast.LENGTH_SHORT).show()
            val i = Intent(this,Main2Activity::class.java)
            startActivity(i)
        }else{
            Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show()
        }
    }
}


