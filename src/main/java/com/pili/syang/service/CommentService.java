package com.pili.syang.service;

import com.pili.syang.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    public  boolean addComment(Comment comment);
    public Page<Comment> getAllComments(Integer vid,Integer num);
    public Page<Comment> getAllCommentsByPraise(Integer vid,Integer num);
    public void addReplies(Integer cid);
    public void addPraise(Integer id);
}
