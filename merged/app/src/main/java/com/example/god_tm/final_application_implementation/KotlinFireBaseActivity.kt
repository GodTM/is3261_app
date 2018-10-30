package com.example.god_tm.final_application_implementation

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView

class KotlinFireBaseActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_kotlin_fire_base)
        supportActionBar!!.hide()





        recyclerView = findViewById(R.id.recyclerView_kotlin_fire_base) ;
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
        urlList.add("i-gZAYBMuBs") ;
        urlList.add("YVu_xSzn2u0&") ;
        urlList.add("PkoGCNtukRU") ;
        urlList.add("tOn5HsQPhUY") ;
        urlList.add("hoF5A30fbu4") ;
        urlList.add("sTk7q3f-t0I") ;
        urlList.add("KEp5RAZNMng") ;
        urlList.add("2pOCfKYO5Ao") ;
        urlList.add("oi-UAwiBigQ") ;
        urlList.add("vS9FWxJUrYc") ;
        urlList.add("ZexkBnk9zaI") ;
        urlList.add("LPqBlIn-Qd4") ;
        urlList.add("mSi7bNk4ySM") ;
        urlList.add("Zy2DKo0v-OY") ;
        urlList.add("-pkweD2s-Oo") ;
        urlList.add("cSMMWOHkP68") ;
        urlList.add("TqbqtAypxLE") ;
        urlList.add("tG6Vqi7Ur9A") ;
        urlList.add("x0ScnHJi8WY") ;
        urlList.add("8bR5hTPufT8") ;
        urlList.add("Yx8lRrclCYo") ;
        urlList.add("-ywVw2O1pP8") ;
        urlList.add("Et8njU58OTs") ;
        urlList.add("jmOEeJ4CFH4") ;
        urlList.add("PZoquOQYNbQ") ;
        urlList.add("mDe_BFXL2vg") ;
        urlList.add("dp3mwaI6SJY") ;
        urlList.add("6G-uiQ_Nrug") ;
        urlList.add("lY_znALrXq4") ;
        urlList.add("6yc_NJ2nagk") ;
        urlList.add("mvzer5fl9sE") ;
        urlList.add("3KX38BVkRzE") ;
        urlList.add("TG55BDSzErw") ;
        urlList.add("mvzer5fl9sE") ;
        urlList.add("3KX38BVkRzE") ;
        urlList.add("TG55BDSzErw") ;
        urlList.add("ASDbrwXeV0c") ;
        urlList.add("SAvQszo-npg") ;
        urlList.add("2iLGmQzHMa0") ;
        urlList.add("9oaaI8m5edI") ;
        urlList.add("dClm5lKzcgM") ;
        urlList.add("NpgKaywzpm0") ;
        urlList.add("w794Z-U2TDU") ;

        return urlList ;


    }


}
