package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class Learn_Classes_Objects_Enum_Classes : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__classes__objects__enum__classes)
        webView = findViewById(R.id.co_enum_classes_webview)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient()
        webView.loadUrl("https://kotlinlang.org/docs/reference/enum-classes.html")
    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }
        else{
            super.onBackPressed()
        }
    }
}
