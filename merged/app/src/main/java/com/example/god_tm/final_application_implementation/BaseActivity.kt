package com.example.god_tm.final_application_implementation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.view.View
import android.content.res.Configuration
import android.widget.*
import com.ikimuhendis.ldrawer.DrawerArrowDrawable
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive
import android.support.v7.app.AppCompatActivity
import com.google.android.youtube.player.internal.ab


class BaseActivity : AppCompatActivity(){
    lateinit var mDrawerToggle:ActionBarDrawerToggle
    lateinit var mDrawerLayout:DrawerLayout
    lateinit var mDrawerList:ListView
    lateinit var drawerArrow:DrawerArrowDrawable
        //,NavigationView.OnNavigationItemSelectedListener {
    lateinit var url:String // to be declared for Practice
    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if (position == -1) setTheme(R.style.AppTheme)
        if (position == 0) setTheme(R.style.AppTheme_Green)
        if (position == 1) setTheme(R.style.AppTheme_Blue)
        if (position == 2) setTheme(R.style.AppTheme_Purple)
        if (position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        changeColorActionBar()
        mDrawerList = findViewById(R.id.navdrawer)
        supportActionBar!!.hide()

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        var learnButton = findViewById<Button>(R.id.learn_main)
        learnButton.setOnClickListener {
            var intent = Intent(this, LearnActivity::class.java)
            startActivity(intent)
        }

        var practiceButton = findViewById<Button>(R.id.practice_main_but)
        practiceButton.setOnClickListener {
            var intent = Intent(this, ActivityWebView::class.java)
            url = "https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt"
            intent.putExtra("url", url)
            startActivity(intent)
        }

        var tutorials = findViewById<Button>(R.id.video_tutorials_button)
        tutorials.setOnClickListener {
            var intent = Intent(this, VideoTutorialsMain::class.java)
            startActivity(intent)
        }


        // links to the editor
        var practiceEditor = findViewById<Button>(R.id.textEditor)
        practiceEditor.setOnClickListener {
            var intent = Intent(this, PracticeMain::class.java)
            startActivity(intent)
        }

        /**val mNavigationView = findViewById<NavigationView>(R.id.navigationView)
        if (mNavigationView != null) {
        mNavigationView.setNavigationItemSelectedListener(this)
        }**/

        drawerArrow = object : DrawerArrowDrawable(this) {
            override fun isLayoutRtl(): Boolean {
                return false
            }
        }
        mDrawerLayout = findViewById<DrawerLayout>(R.id.DrawerLayout_SideBar)
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                invalidateOptionsMenu()
            }
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        drawerArrow.setColor(R.color.ldrawer_color) // to set color
        val values = arrayOf("Account", "Show Progress on Google", "Settings", "Log out")
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values)
        mDrawerList.adapter = adapter
        mDrawerList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val i = Intent(this, AccountActivity::class.java)
                    startActivity(i)
                }
                1 -> {
                }
                2 -> {
                    val i = Intent(this, theme_setting::class.java)
                    startActivity(i)
                }
                3 -> {
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
            }
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
        var ab = actionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
        ab.setHomeButtonEnabled(true)
        ab.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
    }

    /**override fun onNavigationItemSelected(item: MenuItem): Boolean {
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
        if(id == R.id.setting){
            val i = Intent(this,theme_setting::class.java)
            startActivity(i)
        }
        if(id==R.id.nav_account){
            val i = Intent(this,AccountActivity::class.java)
            startActivity(i)
        }
        return false
    }**/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerToggle.setAnimateEnabled(false)
                drawerArrow.setProgress(0f)
                mDrawerLayout.closeDrawer(mDrawerList)

            } else {
                mDrawerToggle.setAnimateEnabled(true)
                drawerArrow.setProgress(1f)
                mDrawerLayout.openDrawer(mDrawerList)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mDrawerToggle.onConfigurationChanged(newConfig)
    }


}
