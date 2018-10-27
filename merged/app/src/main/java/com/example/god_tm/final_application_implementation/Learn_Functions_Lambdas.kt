package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Functions_Lambdas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__functions__lambdas)
        changeColorActionBar()

        var functionsBut = findViewById<Button>(R.id.functions_lambdas_functions_but)
        functionsBut.setOnClickListener {
            var intent = Intent(this ,Learn_Functions_Lambdas_Functions::class.java )
            startActivity(intent)
        }
        var lambdasBut = findViewById<Button>(R.id.functions_lambdas_lambdas_but)
        lambdasBut.setOnClickListener {
            var intent = Intent(this, Learn_Functions_Lambdas_Lambdas::class.java)
            startActivity(intent)
        }

        var inlineFunctionsBut = findViewById<Button>(R.id.functions_lambdas_inline_functions_but)
        inlineFunctionsBut.setOnClickListener {
            var intent = Intent( this , Learn_Functions_Lambdas_Inline_Functions::class.java)
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
