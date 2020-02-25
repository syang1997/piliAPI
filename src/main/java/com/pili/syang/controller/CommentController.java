package com.pili.syang.controller;

import com.pili.syang.BaseInfo;
import com.pili.syang.entity.Comment;
import com.pili.syang.enums.StatusCode;
import com.pili.syang.service.CommentService;
import com.pili.syang.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;
    @Autowired
    private VideoService videoService;

    @PostMapping("/send")
    public BaseInfo sendComment (@RequestBody Comment comment){
        comment.setCreatetime(System.currentTimeMillis());
        comment.setPraise(0);
        comment.setReplies(0);
        comment.setStatu(0);
        commentService.addComment(comment);
        videoService.addReplies(comment.getVid(),1);
        return BaseInfo.BackInfo(StatusCode.SUCCESS, null);

    }
    @PostMapping("/all")
    @ResponseBody
    public BaseInfo allComment (String type,Integer vid,Integer num){
        Page<Comment> allComments=null;
        if ("time".equals(type)){
             allComments = commentService.getAllComments(vid,num);
        }if ("zhan".equals(type)){
            allComments=commentService.getAllCommentsByPraise(vid,num);
        }
         return BaseInfo.BackInfo(StatusCode.SUCCESS,allComments);
    }

    @PostMapping("/zhan")
    @ResponseBody
    public BaseInfo zhan (Integer id){
        commentService.addPraise(id);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,null);
    }
}
