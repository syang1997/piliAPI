package com.pili.syang.controller;

import com.pili.syang.entity.BaseInfo;
import com.pili.syang.entity.Comment;
import com.pili.syang.entity.User;
import com.pili.syang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Autowired
    private CommentService commentService;


    @PostMapping("/send")
    public BaseInfo sendComment (Comment comment){
        comment.setCreatetime(System.currentTimeMillis());
        commentService.addComment(comment);
        return null;
    }
    @PostMapping("/all")
    public BaseInfo allComment (Integer vid){
        List<Comment> allComments = commentService.getAllComments(vid);
         return null;
    }

}
