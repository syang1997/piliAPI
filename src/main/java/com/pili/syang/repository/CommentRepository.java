package com.pili.syang.repository;

import com.pili.syang.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByVid(Integer vid);
}
