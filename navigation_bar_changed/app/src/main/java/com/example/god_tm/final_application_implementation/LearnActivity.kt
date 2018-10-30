package com.example.god_tm.final_application_implementation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LearnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        changeColorActionBar()
        supportActionBar!!.hide()


        var basicTypes= findViewById<Button>(R.id.learn_basics_but)
        var classesObjects = findViewById<Button>(R.id.learn_classes_objects_but)
        var functionsLambdas = findViewById<Button>(R.id.learn_functions_lambdas_but)
        var multiplatform = findViewById<Button>(R.id.learn_mp_but)
        var others = findViewById<Button>(R.id.learn_others_but)


        basicTypes.setOnClickListener {
            var intent = Intent(this,Learn_Basics::class.java)
            startActivity(intent)
        }
        classesObjects.setOnClickListener {
            var intent = Intent(this,Learn_Classes_Objects::class.java)
            startActivity(intent)
        }
        functionsLambdas.setOnClickListener {
            var intent = Intent(this,Learn_Functions_Lambdas::class.java)
            startActivity(intent)
        }
        multiplatform.setOnClickListener {

            var intent = Intent(this,Learn_Multiplatform_Programming::class.java)
            startActivity(intent)
        }
        others.setOnClickListener {

            var intent = Intent(this,Learn_Others::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        changeColorActionBar()
        initialiseButtons()
    }


    fun changeColorActionBar(){
        var actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
    }


    fun initialiseButtons(){

        var basicTypes= findViewById<Button>(R.id.basic_basic_types_but)
        var classesObjects = findViewById<Button>(R.id.learn_classes_objects_but)
        var functionsLambdas = findViewById<Button>(R.id.learn_functions_lambdas_but)
        var multiplatform = findViewById<Button>(R.id.learn_mp_but)
        var others = findViewById<Button>(R.id.learn_others_but)

    }
}
