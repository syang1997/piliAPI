package com.pili.syang.repository;

import com.pili.syang.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Page<Comment> findByVid(Integer vid, Pageable pageable);
    Page<Comment> findByVidOrderByPraiseDesc(Integer vid,Pageable pageable);
    Comment findByCid(Integer cid);
}
