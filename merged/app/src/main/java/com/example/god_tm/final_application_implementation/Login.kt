package com.example.god_tm.final_application_implementation

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
import com.google.android.gms.drive.Drive

class Login : AppCompatActivity() {

    val RC_SIGN_IN = 0
    lateinit var usersDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
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
            addPerson(acct)
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
