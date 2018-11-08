package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.MetadataChangeSet
import org.jsoup.Jsoup
import java.io.OutputStreamWriter
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.youtube.player.internal.e
import com.google.android.youtube.player.internal.l

class ActivityWebView : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var url:String
    lateinit var usersDBHelper: DBHelper
    var file_content=""
    var file_title=""
    lateinit var checked_urls:String
    var points = 0
    val state = static_values()
    var acct:GoogleSignInAccount?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 3) setTheme(R.style.AppTheme_Grey)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val bt_upload = findViewById<Button>(R.id.upload)

        webView = findViewById(R.id.common_webview)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.domStorageEnabled=true
        webView.settings.cacheMode=WebSettings.LOAD_CACHE_ELSE_NETWORK

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webView.webViewClient = MyWebViewClient("")
        url = intent.getStringExtra("url")
        webView.loadUrl(url)

        if(!url.contains("koans") || state.get_status()==0) {
            val layout=findViewById<LinearLayout>(R.id.linearLayout)
            layout.weightSum = 10f
            var lParams  = LinearLayout.LayoutParams(layout.getLayoutParams())
            lParams.height = 0
            lParams.weight = 0f
            bt_upload.setLayoutParams(lParams)
            var rParams  = LinearLayout.LayoutParams(layout.getLayoutParams())
            rParams.height = LinearLayout.LayoutParams.MATCH_PARENT
            rParams.weight  = layout.weightSum
            webView.layoutParams = rParams
        }


        webView.setOnTouchListener { v, event ->
            when (event.action) {
                 MotionEvent.ACTION_UP, MotionEvent.ACTION_MOVE -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        webView.evaluateJavascript(
                                "(function() {return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');})();",
                                { html ->
                                    val temp = html.replace("\\u003C", "<")
                                    val temp3 = Jsoup.parse(temp, "https://play.kotlinlang.org/")
                                    file_content = temp3.getElementsByClass("\\\"koans-editor\\\"").text()
                                    file_title = temp3.getElementsByTag("h2").text()
                                    var output = temp3.getElementsByClass("\\\"test-output\\\"").text()
                                    if (output != "") {
                                        if(!output.contains("Fail") && state.get_status()==1&& !checked_urls.contains(webView.url)){
                                            points = points +1
                                            checked_urls = checked_urls + "\\" + webView.url
                                            usersDBHelper.updateValue(acct!!.email!!,points.toString(),checked_urls)
                                            Toast.makeText(this,"Congratulations! You get one point!"+"\n"+"Your current point:"+
                                                    points,Toast.LENGTH_LONG).show()
                                        }
                                    }
                                })
                    }
                }

            }
            false
        }

        if(state.get_status()==1){
            val acct = GoogleSignIn.getLastSignedInAccount(this)
            usersDBHelper = DBHelper(this)
            val user = usersDBHelper.readPerson(acct!!.email!!)
            points=user[0].points.toInt()
            checked_urls=user[0].urls
            val mDriveResourceClient = Drive.getDriveResourceClient(this, acct!!)
        val rootFolder = mDriveResourceClient.rootFolder
        val createContentsTask = mDriveResourceClient.createContents()
        bt_upload.setOnClickListener {
            try {
                val contents = createContentsTask.result
                val outputStream = contents.outputStream
                val writer = OutputStreamWriter(outputStream)
                //this is the content of the file
                file_content = file_content.replace("\\","")
                writer.write(file_content)
                writer.flush()
                val changeSet = MetadataChangeSet.Builder()
                        //the title of the file
                        .setTitle(file_title)
                        .setMimeType("text/plain")
                        .setStarred(true)
                        .build()
                mDriveResourceClient.createFile(rootFolder.result, changeSet, contents)
                Toast.makeText(this, "upload successfully", Toast.LENGTH_SHORT).show()
                intent.putExtra("url", webView.url)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }}


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
