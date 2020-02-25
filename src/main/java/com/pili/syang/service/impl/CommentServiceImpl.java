package com.pili.syang.service.impl;

import com.pili.syang.entity.Comment;
import com.pili.syang.entity.Revert;
import com.pili.syang.repository.CommentRepository;
import com.pili.syang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Comment> getAllComments(Integer vid,Integer num) {
        Pageable pageable = PageRequest.of(num-1, 10);
        Page<Comment> byVid = commentRepository.findByVid(vid,pageable);
        for (Comment comment:byVid.getContent()){
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

    @Override
    public Page<Comment> getAllCommentsByPraise(Integer vid,Integer num) {
        Pageable pageable = PageRequest.of(num-1, 10);
        Page<Comment> byVid =  commentRepository.findByVidOrderByPraiseDesc(vid,pageable);
        for (Comment comment:byVid.getContent()){
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

    @Override
    public void addReplies(Integer cid) {
        Comment byCid = commentRepository.findByCid(cid);
        byCid.setReplies(byCid.getReplies()+1);
        commentRepository.save(byCid);
    }

    @Override
    public void addPraise(Integer id) {
        Comment byCid = commentRepository.findByCid(id);
        byCid.setPraise(byCid.getPraise()+1);
        commentRepository.save(byCid);
    }
}
