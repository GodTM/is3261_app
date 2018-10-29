package com.example.god_tm.final_application_implementation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class PureKotlinActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView  ;
    lateinit var layoutManager:RecyclerView.LayoutManager
    var youtubeVideos:ArrayList<YoutubeVideos> = ArrayList<YoutubeVideos>() ;
    lateinit var urlList:ArrayList<String>






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pure_kotlin)
        recyclerView = findViewById(R.id.recyclerView_pure_Kotlin) ;
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

        urlList.add("VEqhzCFmEQI") ;
        urlList.add("iC8LRjd67Ds") ;
        urlList.add("mcCt-Dv1lg8")
        urlList.add("sX_-bEpV6M0")
        urlList.add("QUI0PopN1nw")
        urlList.add("G6oVG5XetnE")
        urlList.add("MM1jRsXiWBg")
        urlList.add("LANjqm3Sfpw")
        urlList.add("TGYehzj_NFI")
        urlList.add("KQbVF-GVDU0")
        urlList.add("5GyB6Ndy818")
        urlList.add("r-IEGuDD2rU")
        urlList.add("qAJTqI_aKJU")
        urlList.add("SItGASY92HE")
        urlList.add("EXPZqS8UBrk")
        urlList.add("jNRbbMKOd-E")
        urlList.add("CkvYXSpXS2s")
        urlList.add("2V6et2FdS8I")
        urlList.add("S_DDdmsOyVE")
        urlList.add("ugz2W_yF208")
        urlList.add("FSHjROeqT38")
        urlList.add("cWuVrCxtRqM")
        urlList.add("sLdcnko9LwE")
        urlList.add("fVK7JeHZ3dQ")
        urlList.add("YxixgkS_B1s")
        urlList.add("w3MMiPwU80k")
        urlList.add("UiIfCzu9fTk")
        urlList.add("GSAuDYM_xrA")
        urlList.add("E6WndBrdkSc")
        urlList.add("X8K4eNgzk1s")
        urlList.add("6oFvZKF6KKg")
        urlList.add("ikUF1z_WOZc")
        urlList.add("J2We4LfGZhc")
        urlList.add("5YtmN2V9Om4")
        urlList.add("4i3bN80YkYM")
        urlList.add("903Kv9ByDK0")
        urlList.add("0Zb0TwD9xzg")
        urlList.add("4vHAl7DyMjQ")
        urlList.add("DmSLv-fX8PA")
        urlList.add("0JT2M2jb7Lo")
        urlList.add("nN5KhABIbHQ")
        urlList.add("jea0OEGHi38")
        urlList.add("Z6xj7hta7Ac")
        urlList.add("mK-0Zdjhcuk")
        urlList.add("6Gtsp2M8rr8")
        urlList.add("S0Vkldrh0SE")
        urlList.add("IuPULpQ0q8I")
        urlList.add("dwko93d33Ko")
        urlList.add("kQ7Q8YI9Etk")
        urlList.add("osnaXRyQ350")
        urlList.add("aFXfZH4RRfQ")
        urlList.add("Je_YXshSFmY")
        urlList.add("73RTzuFWqm4")
        urlList.add("MJkCDXhLtDI")
        urlList.add("zEqSP2y1o3s")
        urlList.add("vhzpCAt1LxY")
        urlList.add("I9YijlV1nok")
        urlList.add("f1pHcsoGuFY")
        urlList.add("orUgnUuYW44")
        urlList.add("whc_4PdvHkI")


        return urlList ;
    }






}
