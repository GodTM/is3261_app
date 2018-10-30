package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class VideoTutorialsMain : AppCompatActivity() {

    companion object {

        val BASIC_PROGRAMMING = 1
        val PURE_KOTLIN = 2
        val KOTLIN_ANDROID = 3
        val KOTLIN_FIRE_BASE = 4
        val KOTLIN_ML_KIT = 5
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_tutorials_main)



        var pureProgrammingButton = findViewById<Button>(R.id.programming_beginner)
        pureProgrammingButton.setOnClickListener {
            var intent = Intent(this, PureProgrammingActivity::class.java)
            startActivity(intent)
        }
        var pureKotlinBut         = findViewById<Button>(R.id.pure_kotlin)
        pureKotlinBut.setOnClickListener {
            var intent = Intent(this , PureKotlinActivity::class.java)
            startActivity(intent)
        }

        var kotlinAndroidBut    = findViewById<Button>(R.id.kotlin_for_android)
        kotlinAndroidBut.setOnClickListener {
            var intent = Intent(this , KotlinAndroidActivity::class.java)
            startActivity(intent)
        }

        var kotlinMLBut         = findViewById<Button>(R.id.kotlin_ml_kit)
        kotlinMLBut.setOnClickListener {
            var intent = Intent(this , KotlinMLKitActivity::class.java)
            startActivity(intent)
        }

        var kotlinFireBaseBut     =  findViewById<Button>(R.id.kotlin_android_fire_base)
        kotlinFireBaseBut.setOnClickListener {
            var intent = Intent(this , KotlinFireBaseActivity::class.java)
            startActivity(intent)
        }


        var backBut               = findViewById<Button>(R.id.video_tutorials_main_back_but)
        backBut.setOnClickListener {
            finish()
        }


    }




}
