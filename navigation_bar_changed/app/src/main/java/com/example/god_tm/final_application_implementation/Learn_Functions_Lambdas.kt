package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Functions_Lambdas : AppCompatActivity() {
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__functions__lambdas)
        changeColorActionBar()
        supportActionBar!!.hide()

        var functionsBut = findViewById<Button>(R.id.functions_lambdas_functions_but)
        functionsBut.setOnClickListener {
            var intent = Intent(this ,ActivityWebView::class.java )
            url  = "https://kotlinlang.org/docs/reference/functions.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var lambdasBut = findViewById<Button>(R.id.functions_lambdas_lambdas_but)
        lambdasBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url ="https://kotlinlang.org/docs/reference/lambdas.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var inlineFunctionsBut = findViewById<Button>(R.id.functions_lambdas_inline_functions_but)
        inlineFunctionsBut.setOnClickListener {
            var intent = Intent( this ,ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/inline-functions.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var backBut  = findViewById<Button>(R.id.back_but_functions_lambdas)
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
