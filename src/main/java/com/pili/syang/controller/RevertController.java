package com.pili.syang.controller;

import com.pili.syang.BaseInfo;
import com.pili.syang.entity.Comment;
import com.pili.syang.entity.Revert;
import com.pili.syang.enums.StatusCode;
import com.pili.syang.service.CommentService;
import com.pili.syang.service.RevertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/revert")
public class RevertController extends BaseController{

    @Autowired
    private RevertService revertService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/send")
    public BaseInfo sendRevert (@RequestBody Revert revert){
        revert.setCreatetime(System.currentTimeMillis());
        revert.setPraise(0);
        revert.setReplies(0);
        revert.setStatu(0);
        revertService.addRevert(revert);
        commentService.addReplies(revert.getSuperior());
        return  BaseInfo.BackInfo(StatusCode.SUCCESS, null);
    }

    @PostMapping("/zhan")
    @ResponseBody
    public BaseInfo zhan (Integer id){
        revertService.addPraise(id);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,null);
    }
}
