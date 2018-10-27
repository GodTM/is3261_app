package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class Learn_Others_Operating_Overloading : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__others__operating__overloading)
        webView = findViewById(R.id.others_operator_overloading_webview)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient()
        webView.loadUrl("https://kotlinlang.org/docs/reference/operator-overloading.html")
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
