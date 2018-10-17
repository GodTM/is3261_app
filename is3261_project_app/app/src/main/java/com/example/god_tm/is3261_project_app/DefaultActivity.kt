package com.example.god_tm.is3261_project_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.MenuItem

class DefaultActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout:DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle


    // ask Alex for the functionality
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default)

        mDrawerLayout = findViewById(R.id.DrawerLayout)
        mToggle = ActionBarDrawerToggle(this , mDrawerLayout , R.string.open , R.string.close)

        mDrawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)

        if(mToggle.onOptionsItemSelected(item)){
            mDrawerLayout.openDrawer(GravityCompat.START)
            return true
        }
    }
}
