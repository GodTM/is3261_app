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
import org.jsoup.Jsoup
import java.io.OutputStreamWriter
import java.lang.Exception

class ActivityWebView : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var url: String
    lateinit var db:DBHelper
    lateinit var acct:GoogleSignInAccount
    var point2:Int =0
    lateinit var checked2:String
    var point:Int =0
    lateinit var checked:String

    override fun onCreate(savedInstanceState: Bundle?) {

        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar!!.hide()
        actionBar.hide()
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val db = DBHelper(this)
        var file_title = ""
        var file_content = ""
        val user = db.readPerson(acct!!.email!!)
        point = user[0].points.toInt()
        checked = user[0].urls
        point2=point
        checked2=checked
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

        val show_p = findViewById<Button>(R.id.points)
        val upload_bt = findViewById<Button>(R.id.get)
        webView.webViewClient = MyWebViewClient()
        url = intent.getStringExtra("url")
        if(url !="https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt"){
            show_p.visibility = View.INVISIBLE
            upload_bt.visibility= View.INVISIBLE
        }
        webView.loadUrl(url)


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
                                                checked2 = checked2 + url + "/"
                                                point2 = point2 + 1
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
                writer.flush()
                val changeSet = MetadataChangeSet.Builder()
                        //the title of the file
                        .setTitle(file_title)
                        .setMimeType("text/plain")
                        .setStarred(true)
                        .build()
                mDriveResourceClient.createFile(rootFolder.result, changeSet, contents)
                Toast.makeText(this, "upload successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                //here is a bug. updating works only when the first click
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                recreate()
            }
        }

    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
            if(point2 != point && checked2 != checked) db.updateValue(acct.email!!,point2.toString(),checked2)
        }
    }

}
