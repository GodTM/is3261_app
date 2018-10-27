package com.example.god_tm.final_application_implementation

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.opengl.ETC1.getWidth
import android.content.Context.WINDOW_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.WindowManager
import android.view.Display



class Learn_Basics_BasicTypes : AppCompatActivity() {

    lateinit var webView:WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__basics__basic_types)

        webView = findViewById(R.id.basic_types_webview)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true

        webView.webViewClient = MyWebViewClient()
        webView.loadUrl("https://kotlinlang.org/docs/reference/basic-types.html")

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
