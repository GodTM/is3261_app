package com.example.god_tm.final_application_implementation

import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebViewClient:WebViewClient() {

    // implement try and catch

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

    // for apis over lollipop
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        //return super.shouldOverrideUrlLoading(view, request)
        return true
    }

    // for apis below lollipop
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        view?.loadUrl(url.toString())
        return true
    }


}