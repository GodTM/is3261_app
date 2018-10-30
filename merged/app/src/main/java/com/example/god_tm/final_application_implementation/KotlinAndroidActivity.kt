package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class KotlinAndroidActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView;
    lateinit var layoutManager: RecyclerView.LayoutManager
    var youtubeVideos:ArrayList<YoutubeVideos> = ArrayList<YoutubeVideos>() ;
    lateinit var urlList:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        val state = static_values()
        var position = state.get_position()
        if(position == -1) setTheme(R.style.AppTheme)
        if(position == 0) setTheme(R.style.AppTheme_Green)
        if(position == 1) setTheme(R.style.AppTheme_Blue)
        if(position == 2) setTheme(R.style.AppTheme_Purple)
        if(position == 3) setTheme(R.style.AppTheme_Grey)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_android)
        supportActionBar!!.hide()


        recyclerView = findViewById(R.id.recyclerView_kotlin_android) ;
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)

        var size:Int = 100
        var frameBorder:Int = 0
        urlList = getList()
        for(i in 0..urlList.size-1) {

            var url:String  = urlList.get(i)
            var video:YoutubeVideos = YoutubeVideos("<iframe width=\"${size}%\" height=\"${size}%\" src=\"https://www.youtube.com/embed/${url}\" frameBorder=\"${frameBorder}\" allowfullscreen> </iframe>")
            youtubeVideos.add(video);

        }

        var videoAdapter= VideoAdapter(youtubeVideos )

        recyclerView.adapter = videoAdapter



    }


    fun getList():ArrayList<String>{
        var urlList:ArrayList<String> = ArrayList() ;

        urlList.add("5wbeWN4hQt0")
        urlList.add("qYnZx0lBAFU")
        urlList.add("5XqfcFp2wjg")
        urlList.add("FjNkbpmmsEI")
        urlList.add("bEIxL6EOKLQ")
        urlList.add("oJcsiFsSCVs")
        urlList.add("phcQfdp-5W4")
        urlList.add("sQDm7J5RXrM")
        urlList.add("P564Me4AA3k")
        urlList.add("lZCQZokIL2A")
        urlList.add("k6GSQRnDGog")
        urlList.add("kzpdQf63Adg")
        urlList.add("qUJdUuK8XeI")
        urlList.add("06u_EvuLFKo")
        urlList.add("kUyM4oQxTP4")
        urlList.add("t1oV8J5l_5k")
        urlList.add("H8HGTHbrPeE")
        urlList.add("uEjIDJOKr1s")
        urlList.add("LbHYuqAMhXk")
        urlList.add("6Ke-21QWI8w")
        urlList.add("YqVRapmxD84")
        urlList.add("Aw7zYUPd7oY")
        urlList.add("Y9_nE-Oo7-A")
        urlList.add("3H4ufwNPifk")
        urlList.add("y14So5oxFLg")
        urlList.add("FMjjdsjkpMM")
        urlList.add("M4Jed8FJ1M0")
        urlList.add("HzPmbzIVxDo")
        urlList.add("UIAxKMxNgEU")
        urlList.add("nPXrmZVUyCc")
        urlList.add("Lcw9Puif5IM")
        urlList.add("aeX8PLlWacI")
        urlList.add("Og4E5T933Ds")
        urlList.add("MOVrQ-jn-sU")
        urlList.add("dKCmyGw_e1Q")
        urlList.add("PWY9Gba5oas")
        urlList.add("IPJIjlxRrLI")
        urlList.add("Igup0GRryns")
        urlList.add("ljRPLP-kc9w")
        urlList.add("l6--BWgc6x4")
        urlList.add("ZnRk66VTo0A")
        urlList.add("Dk00XKVwPC0")
        urlList.add("ipJLsGYpZVc")
        urlList.add("BqNE_dacJ3I")
        urlList.add("XWH0WainC1Y")
        urlList.add("hdGFu29ejk8")
        urlList.add("V9xiCh_dTN8")
        urlList.add("53svzHkyRMo")
        urlList.add("jkZPHKQ6BWg")
        urlList.add("vKWP5Ex4OiM")
        urlList.add("dYRYcZ10gIs")
        urlList.add("JVLXBQD0uck")
        urlList.add("2MmmpYdYz-U")






        return urlList ;
    }
}
