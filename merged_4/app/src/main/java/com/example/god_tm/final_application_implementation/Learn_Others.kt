package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Others : AppCompatActivity() {
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__others)
        //changeColorActionBar()


        var annotationsBut= findViewById<Button>(R.id.ot_annotations_but)
        annotationsBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/annotations.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var collectionsBut= findViewById<Button>(R.id.ot_collections_but)
        collectionsBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url ="https://kotlinlang.org/docs/reference/collections.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var destructuringDeclarationsBut = findViewById<Button>(R.id.ot_declarations_but)
        destructuringDeclarationsBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/multi-declarations.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var equalityBut = findViewById<Button>(R.id.ot_equality_but)
        equalityBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/equality.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var exceptionsBut= findViewById<Button>(R.id.ot_exceptions_but)
        exceptionsBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/exceptions.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var nullSafetyBut = findViewById<Button>(R.id.ot_null_safety_button)
        nullSafetyBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/null-safety.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var operatingOverloadingBut= findViewById<Button>(R.id.others_operator_overloading_but)
        operatingOverloadingBut.setOnClickListener {
            var intent = Intent(this ,ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/operator-overloading.html"
            intent.putExtra("url", url)
            startActivity(intent)

        }
        var rangesBut= findViewById<Button>(R.id.ot_ranges_but)
        rangesBut.setOnClickListener {
            var intent = Intent(this ,ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/ranges.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var reflectionsBut= findViewById<Button>(R.id.ot_reflection_but)
        reflectionsBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/reflection.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var tccBut= findViewById<Button>(R.id.ot_tcc_but)
        tccBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/typecasts.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var thisBut= findViewById<Button>(R.id.ot_this_but)
        thisBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/this-expressions.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var tsbBut= findViewById<Button>(R.id.ot_type_safe_builders_but)
        tsbBut.setOnClickListener {
            var intent = Intent(this , ActivityWebView::class.java )
            url = "https://kotlinlang.org/docs/reference/type-safe-builders.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var typeAliasesBut= findViewById<Button>(R.id.ot_type_aliases_but)
        typeAliasesBut.setOnClickListener {
            var intent = Intent(this ,ActivityWebView::class.java )
            url  = "https://kotlinlang.org/docs/reference/type-aliases.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }
        var backBut = findViewById<Button>(R.id.back_others)
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
