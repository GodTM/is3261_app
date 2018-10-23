package com.example.tangx.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.widget.TextView
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.MetadataChangeSet


class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Drive.SCOPE_FILE)
                .requestEmail()
                .build()
        val mGoogleSignInClient =  GoogleSignIn.getClient(this,gso)
        //get current signed user
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        //build a drive client
        val mDriveClient = Drive.getDriveClient(this,acct!!)
        //build a drive resource client
        val mDriveResourceClient = Drive.getDriveResourceClient(this,acct)

        //create a file in google drive
        val btn_drive = findViewById<Button>(R.id.start)
        val folderTask = mDriveResourceClient.rootFolder
        btn_drive.setOnClickListener{
            //val createContentsTask = mDriveResourceClient.createContents()
            //val contents = createContentsTask.result
            //val outputStream = contents.outputStream
            //outputStream.write(10)
            val changeSet = MetadataChangeSet.Builder()
                    .setTitle("New file")
                    .setMimeType("text/plain")
                    .setStarred(true)
                    .build()
            mDriveResourceClient.createFile(folderTask.result,changeSet,null)
        }

        //log out
        val btn_logout = findViewById<Button>(R.id.logout)
        val textview1 = findViewById<TextView>(R.id.status)
        //log out from the current account
        btn_logout.setOnClickListener{
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this) {
                        textview1.setText("Logged Out")
                        val i = Intent(this,MainActivity::class.java)
                        startActivity(i)
                    }
        }
    }
}
