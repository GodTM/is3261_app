package com.example.god_tm.final_application_implementation

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.chinalwb.are.AREditor
import com.commonsware.cwac.richedit.RichEditText
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.MetadataChangeSet
import kotlinx.android.synthetic.main.activity_practice_main.view.*
import java.io.OutputStreamWriter


class PracticeMain : AppCompatActivity() {
    lateinit var url:String
    lateinit var theEditor:AREditor
    lateinit var editorText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_main)
        changeColorActionBar()




        var theEditor = findViewById<AREditor>(R.id.editor)

        val btn_upload = findViewById<Button>(R.id.uploadCode)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val mDriveResourceClient = Drive.getDriveResourceClient(this,acct!!)
        val rootFolder = mDriveResourceClient.rootFolder
        val createContentsTask = mDriveResourceClient.createContents()


        /*
        btn_upload.setOnClickListener {
            val contents = createContentsTask.result
            val outputStream = contents.outputStream
            val writer = OutputStreamWriter(outputStream)
            //this is the content of the file
            writer.write(theEditor.text.toString())
            writer.close()
            val changeSet = MetadataChangeSet.Builder()
                    //the title of the file
                    .setTitle("New file")
                    .setMimeType("text/plain")
                    .setStarred(true)
                    .build()
            mDriveResourceClient.createFile(rootFolder.result,changeSet,contents)
            Toast.makeText(this,"upload successfully", Toast.LENGTH_SHORT).show()
            recreate()
        }*/

    }

    override fun onResume() {
        super.onResume()
        changeColorActionBar()
    }




    fun changeColorActionBar(){
        var actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF1976D2")))
    }

    fun settingUpEditor(editor:RichEditText){
        editor.setKeyboardShortcutsEnabled(true)
        editor.enableActionModes(true) ;

    }
}
