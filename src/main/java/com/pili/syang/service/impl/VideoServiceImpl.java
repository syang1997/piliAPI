package com.pili.syang.service.impl;

import com.pili.syang.entity.Video;
import com.pili.syang.repository.VideoRepository;
import com.pili.syang.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public void upload(Video video) {
        videoRepository.save(video);
    }

    @Override
    public HashMap<String,List<Video>> hotVideos() {
        Pageable pageable = PageRequest.of(0, 8);
        String[] fls = {"动画", "音乐", "舞蹈", "游戏", "科技", "生活", "鬼畜", "时尚", "广告", "娱乐", "影视"};
        HashMap<String,List<Video>> hotMap=new HashMap<>();
        for (String fl:fls) {
            Page<Video> jg = videoRepository.findByClassifyAndHitsGreaterThanOrderByCreatetimeDesc(fl, 100, pageable);
            if (jg.getContent()!=null){
                List<Video> dh = jg.getContent();
                for (Video video: dh){
                    video.getAuthor().setPassword("****");
                    video.getAuthor().setToken("****");
                }
                hotMap.put(fl,dh);
            }
        }
        return hotMap;
    }
    @Override
    public HashMap<String,List<Video>> topVideos(int num) {
        Pageable pageable = PageRequest.of(0, num);
        String[] fls = {"动画", "音乐", "舞蹈", "游戏", "科技", "生活", "鬼畜", "时尚", "广告", "娱乐", "影视"};
        HashMap<String,List<Video>> topMap=new HashMap<>();
        for (String fl:fls) {
            Page<Video> jg = videoRepository.findByClassifyAndCreatetimeGreaterThanOrderByHitsDesc(fl, System.currentTimeMillis()-1000*3600*24*3, pageable);
            if (jg.getContent()!=null){
                List<Video> dh = jg.getContent();
                for (Video video: dh){
                    video.getAuthor().setPassword("****");
                    video.getAuthor().setToken("****");
                }
                topMap.put(fl,dh);
            }
        }
        return topMap;
    }

    @Override
    public List<Video> classifyTop(String classify, int num, long time) {
        Pageable pageable = PageRequest.of(0, num);
        Page<Video> ctops;
        if (classify.equals("全站")){
            ctops =videoRepository.findByCreatetimeGreaterThanOrderByHitsDesc( System.currentTimeMillis()-time, pageable);
        }else {
            ctops =videoRepository.findByClassifyAndCreatetimeGreaterThanOrderByHitsDesc(classify, System.currentTimeMillis()-time, pageable);
        }
        List<Video> content = ctops.getContent();
        for (Video video: content){
            video.getAuthor().setPassword("****");
            video.getAuthor().setToken("****");
        }
        return content;
    }

    @Override
    public Video getVideo(Integer vid) {
        Video byVid = videoRepository.findByVid(vid);
        if (byVid!=null){
            byVid.setHits(byVid.getHits()+1);
            videoRepository.save(byVid);
            return byVid;
        }
        return null;
    }
}
