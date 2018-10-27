package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Learn_Classes_Objects : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__classes__objects)
        changeColorActionBar()
        // list of all Buttons

        var classesInheritanceBut = findViewById<Button>(R.id.co_classes_inheritance_but)
        classesInheritanceBut.setOnClickListener {
            var intent = Intent(this , Learn_Classes_Objects_Classes_Inheritance::class.java)
            startActivity(intent)
        }

        var dataClassBut = findViewById<Button>(R.id.co_data_classes_but)
        dataClassBut.setOnClickListener {
            var intent = Intent(this , Learn_Classes_Objects_Data_Classes::class.java)
            startActivity(intent)
        }


        var delegatedPropertiesBut = findViewById<Button>(R.id.co_delegated_properties)
        delegatedPropertiesBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Delegated_Properties::class.java)
            startActivity(intent)
        }


        var delegationBut= findViewById<Button>(R.id.co_delegation)
        delegationBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Delegation::class.java)
            startActivity(intent)
        }


        var enumBut = findViewById<Button>(R.id.co_enums_but)
        enumBut.setOnClickListener {
                    var intent = Intent(this, Learn_Classes_Objects_Enum_Classes::class.java)
                    startActivity(intent)
                }

        var extensionBut = findViewById<Button>(R.id.co_extensions_but)
        extensionBut.setOnClickListener {
                    var intent = Intent(this, Learn_Classes_Objects_Extensions::class.java)
                    startActivity(intent)
                }


        var genericsBut = findViewById<Button>(R.id.co_generics_but)
        genericsBut.setOnClickListener {
                    var intent = Intent(this, Learn_Classes_Objects_Generics::class.java)
                    startActivity(intent)
                }

        var inlineClassesBut = findViewById<Button>(R.id.co_inline_classes)
        inlineClassesBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Inline_Classes::class.java)
            startActivity(intent)
        }


        var interfacesBut = findViewById<Button>(R.id.co_interfaces_but)
        interfacesBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Interfaces::class.java)
            startActivity(intent)
        }

        var nestedClassesBut= findViewById<Button>(R.id.co_nested_classes_but)
        nestedClassesBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Nested_Classes::class.java)
            startActivity(intent)
        }

        var objectsBut= findViewById<Button>(R.id.co_objects)
        objectsBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Objects::class.java)
            startActivity(intent)
        }

        var propertiesFieldsBut= findViewById<Button>(R.id.co_properties_fields_but)
        propertiesFieldsBut.setOnClickListener {
                    var intent = Intent(this, Learn_Classes_Objects_Properties_Fields::class.java)
                    startActivity(intent)
                }

        var sealedClassesBut = findViewById<Button>(R.id.co_sealed_classes_but)
        sealedClassesBut.setOnClickListener {
                    var intent = Intent(this, Learn_Classes_Objects_Sealed_Classes::class.java)
                    startActivity(intent)
                }

        var visibilityModifiersBut = findViewById<Button>(R.id.co_visibility_modifiers_but)
        visibilityModifiersBut.setOnClickListener {
            var intent = Intent(this, Learn_Classes_Objects_Visibility_Modifiers::class.java)
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
