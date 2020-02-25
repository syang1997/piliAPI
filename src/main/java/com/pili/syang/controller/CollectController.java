package com.pili.syang.controller;

import com.pili.syang.BaseInfo;
import com.pili.syang.entity.Collect;
import com.pili.syang.enums.StatusCode;
import com.pili.syang.service.CollectService;
import com.pili.syang.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;
    @Autowired
    private VideoService videoService;



    @RequestMapping("/add")
    public BaseInfo addCollect(Collect collect){
        collectService.addCollect(collect);
        videoService.addCollect(collect.getVid().getVid(),1);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,null);
    }

    @RequestMapping("/cancel")
    public BaseInfo decCollect(Collect collect){
        collectService.decCollect(collect);
        videoService.addCollect(collect.getVid().getVid(),-1);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,null);
    }

    @RequestMapping("/check")
    public BaseInfo checkCollect(Collect collect){
        boolean b = collectService.checkCollect(collect);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,b);
    }

    @RequestMapping("/all")
    public BaseInfo allCollect(Integer uid,Integer num){
        Page<Collect> collects = collectService.allCollect(uid, num);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,collects);
    }
}
