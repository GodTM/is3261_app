package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Classes_Objects : AppCompatActivity() {
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__classes__objects)
        changeColorActionBar()
        // list of all Buttons


        var classesInheritanceBut = findViewById<Button>(R.id.co_classes_inheritance_but)
        classesInheritanceBut.setOnClickListener {
            url = "https://kotlinlang.org/docs/reference/classes.html"
            var intent = Intent(this , ActivityWebView::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var dataClassBut = findViewById<Button>(R.id.co_data_classes_but)
        dataClassBut.setOnClickListener {
            url = "https://kotlinlang.org/docs/reference/data-classes.html"
            var intent = Intent(this ,ActivityWebView::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }


        var delegatedPropertiesBut = findViewById<Button>(R.id.co_delegated_properties)
        delegatedPropertiesBut.setOnClickListener {
            url = "https://kotlinlang.org/docs/reference/delegated-properties.html"
            var intent = Intent(this, ActivityWebView::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }


        var delegationBut= findViewById<Button>(R.id.co_delegation)
        delegationBut.setOnClickListener {
            url = "https://kotlinlang.org/docs/reference/delegation.html"
            var intent = Intent(this, ActivityWebView::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }


        var enumBut = findViewById<Button>(R.id.co_enums_but)
        enumBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/enum-classes.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var extensionBut = findViewById<Button>(R.id.co_extensions_but)
        extensionBut.setOnClickListener {

            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/extensions.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }


        var genericsBut = findViewById<Button>(R.id.co_generics_but)
        genericsBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/generics.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var inlineClassesBut = findViewById<Button>(R.id.co_inline_classes)
        inlineClassesBut.setOnClickListener {
            url = "https://kotlinlang.org/docs/reference/inline-classes.html"
            var intent = Intent(this,ActivityWebView::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }


        var interfacesBut = findViewById<Button>(R.id.co_interfaces_but)
        interfacesBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/interfaces.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var nestedClassesBut= findViewById<Button>(R.id.co_nested_classes_but)
        nestedClassesBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/nested-classes.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var objectsBut= findViewById<Button>(R.id.co_objects)
        objectsBut.setOnClickListener {
            var intent = Intent(this,ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/object-declarations.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var propertiesFieldsBut= findViewById<Button>(R.id.co_properties_fields_but)
        propertiesFieldsBut.setOnClickListener {
            var intent = Intent(this,ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/properties.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var sealedClassesBut = findViewById<Button>(R.id.co_sealed_classes_but)
        sealedClassesBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/sealed-classes.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var visibilityModifiersBut = findViewById<Button>(R.id.co_visibility_modifiers_but)
        visibilityModifiersBut.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://kotlinlang.org/docs/reference/visibility-modifiers.html"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var backBut = findViewById<Button>(R.id.back_but_classes_objects)
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
