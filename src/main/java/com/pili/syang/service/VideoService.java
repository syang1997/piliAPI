package com.pili.syang.service;

import com.pili.syang.entity.Video;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface VideoService {
    void upload(Video video);
    HashMap<String,List<Video>> hotVideos();
    HashMap<String,List<Video>> topVideos(int num);
    List<Video> classifyTop(String classify,int num,long time);
    Video getVideo(Integer vid);
}
