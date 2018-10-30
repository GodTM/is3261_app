package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Multiplatform_Programming : AppCompatActivity() {
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__multiplatform__programming)
        changeColorActionBar()


        var mpPsdBut = findViewById<Button>(R.id.mp_psd_but)
        mpPsdBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/platform-specific-declarations.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var mpBgBut  = findViewById<Button>(R.id.mp_bg_but)
        mpBgBut.setOnClickListener {
            var intent = Intent(this ,ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var backBut = findViewById<Button>(R.id.back_but_mp)
        backBut.setOnClickListener {
            finish()
        }
    }


    override fun onResume() {
        super.onResume()
        changeColorActionBar()
    }

    override fun onPause(){
        super.onPause()
    }

    override fun onDestroy(){
        super.onDestroy()
    }

    fun changeColorActionBar(){
        var actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
    }
}
