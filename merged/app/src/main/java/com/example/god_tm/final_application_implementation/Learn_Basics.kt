package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Basics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


            val state = static_values()
            var position = state.get_position()
            if(position == -1) setTheme(R.style.AppTheme)
            if(position == 0) setTheme(R.style.AppTheme_Green)
            if(position == 1) setTheme(R.style.AppTheme_Blue)
            if(position == 2) setTheme(R.style.AppTheme_Purple)
            if(position == 3) setTheme(R.style.AppTheme_Grey)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__basics)
        //changeColorActionBar()

        var basicTypesBut = findViewById<Button>(R.id.basic_basic_types_but)
        basicTypesBut.setOnClickListener {
            var intent= Intent(this, Learn_Basics_BasicTypes::class.java)
            startActivity(intent)
        }

        var packagesImportsBut = findViewById<Button>(R.id.basics_packages_imports)
        packagesImportsBut.setOnClickListener{
            var intent = Intent(this,Learn_Basics_Packages_Imports::class.java)
            startActivity(intent)
        }

        var controlFlowBut = findViewById<Button>(R.id.basics_control_flow)
        controlFlowBut.setOnClickListener {
            var intent = Intent(this , Learn_Basics_Control_Flow::class.java)
            startActivity(intent)
        }

        var returnJumpsBut = findViewById<Button>(R.id.basics_returns_jumps_but)
        returnJumpsBut.setOnClickListener {
            var intent = Intent(this , Learn_Basics_Returns_Jumps::class.java)
            startActivity(intent)
        }

        var backBut = findViewById<Button>(R.id.basics_back_but)
        backBut.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        //changeColorActionBar()
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
