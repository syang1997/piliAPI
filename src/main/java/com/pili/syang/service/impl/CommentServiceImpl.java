package com.pili.syang.service.impl;

import com.pili.syang.entity.Comment;
import com.pili.syang.entity.Revert;
import com.pili.syang.repository.CommentRepository;
import com.pili.syang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean addComment(Comment comment) {
        try {

            Comment save = commentRepository.save(comment);
            if (save==null){
                return false;
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public List<Comment> getAllComments(Integer vid) {
        List<Comment> byVid = commentRepository.findByVid(vid);
        for (Comment comment:byVid){
            comment.getPublisher().setToken("");
            comment.getPublisher().setPassword("ss");
            if (comment.getSends()!=null){
                for (Revert revert:comment.getSends()){
                    revert.getAcceptor().setPassword("ss");
                    revert.getAcceptor().setToken("ss");
                    revert.getPublisher().setPassword("ss");
                    revert.getPublisher().setToken("ss");
                }
            }
        }
        return byVid;
    }
}
