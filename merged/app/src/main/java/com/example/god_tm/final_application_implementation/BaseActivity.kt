package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.MetadataChangeSet
import java.io.OutputStreamWriter

class BaseActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var url:String // to be declared for Practice
    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        Toast.makeText(this,position.toString(),Toast.LENGTH_SHORT).show()
        changeColorActionBar()
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        var learnButton = findViewById<Button>(R.id.learn_main)
        learnButton.setOnClickListener {
            var intent = Intent(this, LearnActivity::class.java)
            startActivity(intent)
        }

        var practiceButton = findViewById<Button>(R.id.practice_main_but)
        practiceButton.setOnClickListener {

            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt"
            intent.putExtra("url", url)
            startActivity(intent)

        }

        var tutorials = findViewById<Button>(R.id.video_tutorials_button)
        tutorials.setOnClickListener {


            var intent = Intent(this, TutorialsActivity::class.java)
            startActivity(intent)
        }


        // links to the editor
        var practiceEditor = findViewById<Button>(R.id.textEditor)
        practiceEditor.setOnClickListener {

            var intent  = Intent(this , PracticeMain::class.java)
            startActivity(intent)
        }

        val mNavigationView = findViewById<NavigationView>(R.id.navigationView)
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this)
        }
        val btn_upload = findViewById<Button>(R.id.upload)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val mDriveResourceClient = Drive.getDriveResourceClient(this,acct!!)
        val rootFolder = mDriveResourceClient.rootFolder
        val createContentsTask = mDriveResourceClient.createContents()
        btn_upload.setOnClickListener {
            val contents = createContentsTask.result
            val outputStream = contents.outputStream
            val writer = OutputStreamWriter(outputStream)
            //this is the content of the file
            writer.write("hello,world!")
            writer.close()
            val changeSet = MetadataChangeSet.Builder()
                    //the title of the file
                    .setTitle("New file")
                    .setMimeType("text/plain")
                    .setStarred(true)
                    .build()
            mDriveResourceClient.createFile(rootFolder.result,changeSet,contents)
            Toast.makeText(this,"upload successfully",Toast.LENGTH_SHORT).show()
            recreate()
        }
    }


    override fun onResume() {
        super.onResume()
        //changeColorActionBar()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun changeColorActionBar() {
        var actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //log out
        if (id == R.id.log_out) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestScopes(Drive.SCOPE_FILE)
                    .requestEmail()
                    .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this) {
                        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, Login::class.java)
                        startActivity(i)
                    }
        }
        //upload file onto google drive
        if(id == R.id.setting){
            val i = Intent(this,theme_setting::class.java)
            startActivity(i)
        }
        return false
    }





}
