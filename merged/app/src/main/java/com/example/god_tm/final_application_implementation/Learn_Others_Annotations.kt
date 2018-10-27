package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class Learn_Others_Annotations : AppCompatActivity() {
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn__others__annotations)
        webView = findViewById(R.id.others_annotations_webview)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.webViewClient = MyWebViewClient()
        webView.loadUrl("https://kotlinlang.org/docs/reference/annotations.html")
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

