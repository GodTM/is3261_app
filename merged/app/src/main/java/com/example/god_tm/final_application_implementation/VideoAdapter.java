package com.example.god_tm.final_application_implementation ;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


import java.util.ArrayList;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    ArrayList<YoutubeVideos> youtubeVideosList ;
    int id  ;

    public VideoAdapter(){}
    public VideoAdapter(ArrayList<YoutubeVideos> videos){

        this.youtubeVideosList = videos ;

    }


    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_view_tutorial_layout, viewGroup, false);


        return new VideoViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder , int position) {
        holder.videoWeb.loadData(youtubeVideosList.get(position).getVideoUrl(), "text/html", "utf-8") ;
    }

    @Override
    public int getItemCount() {
        return this.youtubeVideosList.size() ;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        private int videoHolderId = id ;
        WebView videoWeb ;
        public VideoViewHolder(View itemView){
            super(itemView) ;

            videoWeb = (WebView) itemView.findViewById(R.id.web_view_tutorials) ;
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient()) ;

            speedUp(videoWeb);



        }



        public void speedUp(WebView webView){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // chromium, enable hardware acceleration
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                // older android version, disable hardware acceleration
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


            }
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }


        }








    }





