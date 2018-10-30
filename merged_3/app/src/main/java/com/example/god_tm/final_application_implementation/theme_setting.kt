package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton

class theme_setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_setting)

        val bt_dft = findViewById<RadioButton>(R.id.dft)
        bt_dft.setOnClickListener{
            state.set(-1)
            recreate()
        }

        val bt_green = findViewById<RadioButton>(R.id.green)
        bt_green.setOnClickListener{
            state.set(0)
            recreate()
        }

        val bt_blue = findViewById<RadioButton>(R.id.blue)
        bt_blue.setOnClickListener{
            state.set(1)
            recreate()
        }

        val bt_purple = findViewById<RadioButton>(R.id.purple)
        bt_purple.setOnClickListener{
            state.set(2)
            recreate()
        }

        val bt_grey = findViewById<RadioButton>(R.id.grey)
        bt_grey.setOnClickListener{
            state.set(3)
            recreate()
        }

        val bt_ok = findViewById<Button>(R.id.ok)
        bt_ok.setOnClickListener {
            //finish()
            val i = Intent(this,BaseActivity::class.java)
            startActivity(i)
        }
    }
}
