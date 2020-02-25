package com.pili.syang.controller;

import com.pili.syang.BaseInfo;
import com.pili.syang.entity.Concern;
import com.pili.syang.entity.User;
import com.pili.syang.entity.Video;
import com.pili.syang.enums.StatusCode;
import com.pili.syang.repository.UserRepository;
import com.pili.syang.service.ConcernService;
import com.pili.syang.service.UserService;
import com.pili.syang.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/concern")
public class ConcernController extends BaseController{

    @Autowired
    private ConcernService concernService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public BaseInfo addConcern(  Concern concern){
        concernService.addConcern(concern);
        userService.addFans(concern.getUp().getUid());
        return BaseInfo.BackInfo(StatusCode.SUCCESS,null);
    }

    @PostMapping("/check")
    public BaseInfo isConcerned( Concern concern){
        boolean chackCoern = concernService.chackCoern(concern.getFans(), concern.getUp());
        return BaseInfo.BackInfo(StatusCode.SUCCESS,chackCoern);
    }

    @PostMapping("/cancel")
    public BaseInfo cancelConcern(  Concern concern){
        concernService.delConcern(concern);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,null);
    }

    @PostMapping("/all")
    public BaseInfo allConcerns(Integer uid){
        List<Concern> allConcern = concernService.getAllConcern(uid);
        for (Concern concern:allConcern){
            concern.getUp().setPassword("**");
            concern.getUp().setToken("**");
        }
        return BaseInfo.BackInfo(StatusCode.SUCCESS,allConcern);
    }

    @PostMapping("/dynamic")
    public BaseInfo getdynamic(Integer uid,Integer num){
        List<Concern> allConcern = concernService.getAllConcern(uid);
        List<User> ups=new LinkedList<>();
        for (Concern concern:allConcern){
            ups.add(concern.getUp());
        }
        List<Video> dynamic = videoService.dynamic(ups, num - 1);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,dynamic);
    }
}
