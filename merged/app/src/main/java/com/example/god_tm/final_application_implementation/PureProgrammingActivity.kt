package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class PureProgrammingActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_pure_programming)
        supportActionBar!!.hide()


        recyclerView = findViewById(R.id.recyclerView_pure_programming) ;
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
        urlList.add("y62zj9ozPOM") ;
        urlList.add("EApk15pCIEA")
        urlList.add("IJNPHorTqQs")
        urlList.add("U9o49qwa6hk")
        urlList.add("Zn8OJMYT-gc")
        urlList.add("eZQBx8YJ6Zs")
        urlList.add("PUPDGbnpSjw")
        urlList.add("0y5UkZc-C8Y")
        urlList.add("n_8zxTH7SvA")
        urlList.add("icOod04jYww")
        urlList.add("MaqfxpCBMJI")
        urlList.add("4qQW1uSoxRg")
        urlList.add("82zBV81rJC8")

        return urlList ;
    }
}
