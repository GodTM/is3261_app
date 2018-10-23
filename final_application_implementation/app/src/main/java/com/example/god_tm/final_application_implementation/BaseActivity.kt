package com.example.god_tm.final_application_implementation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        changeColorActionBar()

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        var learnButton = findViewById<Button>(R.id.learn_main)
        learnButton.setOnClickListener {

            var intent = Intent(this , LearnActivity::class.java)
            startActivity(intent)
        }

        var practiceButton = findViewById<Button>(R.id.practice_main_but)
        practiceButton.setOnClickListener {

            var intent = Intent(this , PracticeMain::class.java)
            startActivity(intent)

        }

        var tutorials = findViewById<Button>(R.id.video_tutorials_button)
        tutorials.setOnClickListener {


            var intent = Intent(this , TutorialsActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onResume() {
        super.onResume()
        changeColorActionBar()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun changeColorActionBar(){
        var actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
    }




}
