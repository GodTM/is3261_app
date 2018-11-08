package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class QaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qa)
        val bt_ok = findViewById<Button>(R.id.ok)
        val edit_question = findViewById<EditText>(R.id.question)
        bt_ok.setOnClickListener {
            val question_text = edit_question.text.toString().replace(" ","+")
            var intent = Intent(this, ActivityWebView::class.java)
            val url = "https://stackoverflow.com/search?q="+question_text
            intent.putExtra("url", url)
            startActivity(intent)
        }
    }
}
