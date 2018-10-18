package com.example.god_tm.is3261_project_app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.text.FieldPosition

class ViewPagerAdapter(fm: FragmentManager):FragmentPagerAdapter(fm){

    private  var fragmentList: MutableList<Fragment> = ArrayList<Fragment>()
    private  var fragmentListTitles :MutableList<String> = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentListTitles.size

    }

    override fun getItem(position: Int): Fragment {

        return fragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentListTitles.get(position)
    }

    fun addFragment(fragment:Fragment , title : String){

        fragmentList.add(fragment)
        fragmentListTitles.add(title)
    }
}