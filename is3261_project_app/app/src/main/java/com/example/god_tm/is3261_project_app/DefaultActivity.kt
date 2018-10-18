package com.example.god_tm.is3261_project_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.MenuItem

class DefaultActivity : AppCompatActivity() {
    // varibles for titlebar and the drawerlayout
    private lateinit var mDrawerLayout:DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle


    // variables for the tab Layout
    private lateinit var tabLayout:TabLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var viewPager: ViewPager


    // variables for the tabLayout and

    // ask Alex for the functionality
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)

        mDrawerLayout = findViewById(R.id.DrawerLayout)
        mToggle = ActionBarDrawerToggle(this , mDrawerLayout , R.string.open , R.string.close)

        mDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)





        tabLayout = findViewById(R.id.tabLayout_id)
        appBarLayout = findViewById(R.id.appbarid)// whjy ??
        viewPager = findViewById(R.id.viewpager_id)

        var adapter:ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        var practiceFragment = Practice()
        var lessonFragment = Lessons()
        var tutorials = Tutorials()

        // adding fragments
        adapter.addFragment(practiceFragment ,"Practice")
        adapter.addFragment(lessonFragment,"Lessons")
        adapter.addFragment(tutorials , "Tutorials")

        // adapter setup
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)

        if(mToggle.onOptionsItemSelected(item)){
            mDrawerLayout.openDrawer(GravityCompat.START)
            return true
        }
    }
}
