package com.pili.syang.service;

import com.pili.syang.entity.Comment;

import java.util.List;

public interface CommentService {
    public  boolean addComment(Comment comment);
    public List<Comment> getAllComments(Integer vid);
}
