package com.example.god_tm.final_application_implementation

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class ActivityWebView : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var url:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()

        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        webView = findViewById(R.id.common_webview)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true

        // no cache
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE)
        val webSettings = webView.settings
        webSettings.domStorageEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }


        webView.webViewClient = MyWebViewClient()
        url = intent.getStringExtra("url")
        webView.loadUrl(url)

        val textView1 = findViewById<TextView>(R.id.input)
        val textView2 = findViewById<TextView>(R.id.output)
        val get = findViewById<Button>(R.id.get)

        get.setOnClickListener {
            try{
                webView.getSettings().setJavaScriptEnabled(true)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript(
                            "(function() {return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');})();",
                            { html ->
                                val temp = html.replace("\\u003C","<")
                                val temp2 = Jsoup.parse(temp,"https://try.kotlinlang.org/")
                                val input = temp2.getElementsByTag("pre").text()
                                val output = temp2.getElementsByClass("\\\"standard-output\\\"")
                                if(output != null){
                                    val real_output = output.text().replace("\\n","")
                                    textView2.setText(real_output)
                                }
                                textView1.setText(input)
                            })
                }
            }catch (e: Exception){
                Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
            }
        }

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
