package com.example.god_tm.final_application_implementation ;

public class YoutubeVideos {

    String videoUrl ;
    public YoutubeVideos(){}
    public YoutubeVideos(String url){
        this.videoUrl = url ;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String url){
        this.videoUrl = url ;
    }
}
