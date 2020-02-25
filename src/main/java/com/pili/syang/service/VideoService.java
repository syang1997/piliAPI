package com.pili.syang.service;

import com.pili.syang.entity.User;
import com.pili.syang.entity.Video;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VideoService {
    void upload(Video video);
    HashMap<String,List<Video>> hotVideos();
    HashMap<String,List<Video>> topVideos(int num);
    List<Video> classifyTop(String classify,int num,long time);
    Video getVideo(Integer vid);
    void addReplies(Integer video,Integer num);
    void addCollect(Integer video,Integer num);
    List<Video> dynamic(List<User> ups,Integer num);
    Page<Video> myvideos(User user,Integer num);
    void updataVideo(Video video);
    void deleteVideo(Video video);
    Object[] findDomainAndCount(Integer uid);
    Integer[] findcountClassity();
}
