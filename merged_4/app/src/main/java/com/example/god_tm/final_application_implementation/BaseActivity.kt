package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ShareCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive
import java.lang.Exception

class BaseActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var url:String // to be declared for Practice
    lateinit var usersDBHelper: DBHelper
    lateinit var callbackManager: CallbackManager
    lateinit var shareDialog:ShareDialog
    var points = ""
    lateinit var mNavigationView:NavigationView
    lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(DrawerArrowDrawable(this))
        usersDBHelper=DBHelper(this)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        val user = usersDBHelper.readPerson(acct!!.email!!)
        points =user[0].points

        var learnButton = findViewById<Button>(R.id.learn_main)
        learnButton.setOnClickListener {
            var intent = Intent(this, LearnActivity::class.java)
            startActivity(intent)
        }

        var practiceButton = findViewById<Button>(R.id.practice_main_but)
        practiceButton.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            //url = "https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS4zLVJDIiwicGxhdGZvcm0iOiJqYXZhIiwiYXJncyI6IiIsIm5vbmVNYXJrZXJzIjp0cnVlLCJ0aGVtZSI6ImlkZWEiLCJmb2xkZWRCdXR0b24iOnRydWUsInJlYWRPbmx5IjpmYWxzZSwiY29kZSI6Ii8qKlxuICogWW91IGNhbiBlZGl0LCBydW4sIGFuZCBzaGFyZSB0aGlzIGNvZGUuIFxuICogcGxheS5rb3RsaW5sYW5nLm9yZyBcbiAqL1xuXG5mdW4gbWFpbigpIHtcbiAgICBwcmludGxuKFwiSGVsbG8sIHdvcmxkISEhXCIpXG59In0="
            //alternate
            url = "https://play.kotlinlang.org/koans/Introduction/Hello,%20world!/Task.kt"
            intent.putExtra("url", url)
            startActivity(intent)

        }

        var tutorials = findViewById<Button>(R.id.video_tutorials_button)
        tutorials.setOnClickListener {
            var intent = Intent(this, VideoTutorialsMain::class.java)
            startActivity(intent)
        }

        mNavigationView = findViewById(R.id.navigationView)
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this)
        }

    }


    override fun onResume() {
        super.onResume()
        //changeColorActionBar()
    }

    override fun onPause() {
        super.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
    }
    fun changeColorActionBar() {
        var actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        //log out
        if (id == R.id.log_out) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestScopes(Drive.SCOPE_FILE)
                    .requestEmail()
                    .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this) {
                        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, Login::class.java)
                        startActivity(i)
                    }
        }
        //upload file onto google drive
        if(id == R.id.setting){
            val i = Intent(this,theme_setting::class.java)
            startActivity(i)
        }
        //share to google+
        if(id == R.id.share_progress_g_plus){
            callbackManager = CallbackManager.Factory.create()
            val shareIntent = ShareCompat.IntentBuilder.from(this)
                    .setText("I have got "+points+" points in kotlin learning!")
                    .setType("text/plain")
                    .intent
                    .setPackage("com.google.android.apps.plus")
            try {
                startActivityForResult(shareIntent,1)
            }catch (e: Exception){
                Toast.makeText(this,"Please install Google+ on your phone",Toast.LENGTH_SHORT).show()
            }
        }
        //share to facebook
        if(id == R.id.share_progress_fb){
            FacebookSdk.setApplicationId("722472751466283")
            FacebookSdk.sdkInitialize(this)
            shareDialog = ShareDialog(this)
            callbackManager = CallbackManager.Factory.create()
            val content = ShareLinkContent.Builder()
                    .setContentUrl((Uri.parse("https://play.kotlinlang.org/koans/overview")))
                    .setQuote("I have got "+points+" points in kotlin learning!")
                    .setShareHashtag(ShareHashtag.Builder().setHashtag("#Kotlin_Learning")
                            .build())
                    .build()
            shareDialog.show(content)
        }
        if(id == R.id.nav_account){
            val i = Intent(this,AccountActivity::class.java)
            startActivity(i)
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item:MenuItem):Boolean {
        if(item.itemId == android.R.id.home){
            drawerLayout = findViewById(R.id.DrawerLayout_SideBar)
            if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                drawerLayout.closeDrawer(Gravity.LEFT)
            }else{
            drawerLayout.openDrawer(Gravity.LEFT)}
        }
        return true
    }





}
