package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class KotlinMLKitActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView;
    lateinit var layoutManager: RecyclerView.LayoutManager
    var youtubeVideos:ArrayList<YoutubeVideos> = ArrayList<YoutubeVideos>() ;
    lateinit var urlList:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_mlkit)


        recyclerView = findViewById(R.id.recyclerView_kotlin_mlkit) ;
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
        urlList.add("uTl5iS9XEhg")
        urlList.add("V5HaTvI2rPk")
        urlList.add("qbJ50J4nZiM")
        return urlList ;
    }
}
