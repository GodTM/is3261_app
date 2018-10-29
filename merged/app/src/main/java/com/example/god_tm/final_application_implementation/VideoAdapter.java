package com.example.god_tm.final_application_implementation ;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


import com.example.god_tm.final_application_implementation.R;

import java.util.ArrayList;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    ArrayList<YoutubeVideos> youtubeVideosList ;

    public VideoAdapter(){}
    public VideoAdapter(ArrayList<YoutubeVideos> videos){

        this.youtubeVideosList = videos ;
    }


    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_view_pure_kotlin,viewGroup ,false) ;

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

        WebView videoWeb ;
        public VideoViewHolder(View itemView){
            super(itemView) ;

        videoWeb = (WebView) itemView.findViewById(R.id.web_view_pure_kotlin) ;
        videoWeb.getSettings().setJavaScriptEnabled(true);
        videoWeb.setWebChromeClient(new WebChromeClient()) ;

        }


    }




}
