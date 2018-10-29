package com.example.god_tm.final_application_implementation

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.drive.Drive
import com.google.android.gms.drive.MetadataChangeSet
import com.google.android.youtube.player.internal.e
import org.jsoup.Jsoup
import java.io.OutputStreamWriter
import java.lang.Exception

class ActivityWebView : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var url: String
    lateinit var checked: String
    lateinit var db:DBHelper
    lateinit var acct:GoogleSignInAccount
    lateinit var point:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val db = DBHelper(this)
        var file_title = ""
        var file_content = ""
        val user = db.readPerson(acct!!.email!!)
        var point = user[0].points.toInt()
        checked = user[0].urls
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
        val show_p = findViewById<Button>(R.id.points)
        val upload_bt = findViewById<Button>(R.id.get)

        //webview onclick listener
        webView.setOnTouchListener({ v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP, MotionEvent.ACTION_MOVE -> {
                    try {
                        webView.getSettings().setJavaScriptEnabled(true)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            webView.evaluateJavascript(
                                    "(function() {return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');})();",
                                    { html ->
                                        val temp = html.replace("\\u003C", "<")
                                        val temp2 = Jsoup.parse(temp, "https://try.kotlinlang.org/")
                                        val input = temp2.getElementsByTag("pre").text()
                                        val output = temp2.getElementById("\\\"program-output\\\"")
                                        file_title = temp2.getElementsByTag("title").text()
                                        file_content = input
                                        if (output != null) {
                                            val real_output = output.text().replace("\\n", "")
                                            Toast.makeText(this, "output:"+real_output, Toast.LENGTH_SHORT).show()
                                            if (!checked.contains(url) && real_output.contains("All tests passed")) {
                                                checked = checked + url + "/"
                                                point = point + 1
                                            }
                                        }
                                    })
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                    }

                }
            }//ontouch
            false
        })

        //show points
        show_p.setOnClickListener {
            try {
                val point_updated = point.toString()
                db.updateValue(user[0].mail, point_updated, checked)
                Toast.makeText(this, "points:" + user[0].points, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
        val mDriveResourceClient = Drive.getDriveResourceClient(this, acct!!)
        val rootFolder = mDriveResourceClient.rootFolder
        val createContentsTask = mDriveResourceClient.createContents()
        upload_bt.setOnClickListener {
            try {
                val contents = createContentsTask.result
                val outputStream = contents.outputStream
                val writer = OutputStreamWriter(outputStream)
                //this is the content of the file
                writer.write(file_content)
                //writer.close()
                val changeSet = MetadataChangeSet.Builder()
                        //the title of the file
                        .setTitle(file_title)
                        .setMimeType("text/plain")
                        .setStarred(true)
                        .build()
                mDriveResourceClient.createFile(rootFolder.result, changeSet, contents)
                Toast.makeText(this, "upload successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.d("ERROR", e.toString())
            }
        }

    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
            db.updateValue(acct.email!!,point,checked)
        }
    }

}
