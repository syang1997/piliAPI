package com.pili.syang.controller;

import com.pili.syang.entity.BaseInfo;
import com.pili.syang.entity.Comment;
import com.pili.syang.entity.Revert;
import com.pili.syang.service.RevertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revert")
public class RevertController extends BaseController{

    @Autowired
    private RevertService revertService;

    @PostMapping("/send")
    public BaseInfo sendRevert (Revert revert){
        revert.setCreatetime(System.currentTimeMillis());
        revertService.addRevert(revert);
        return null;
    }
}
