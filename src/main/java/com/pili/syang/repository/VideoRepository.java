package com.pili.syang.repository;

import com.pili.syang.entity.User;
import com.pili.syang.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
   public List<Video> findByAuthorOrderByCreatetime(User user);
   public Page<Video> findByClassifyAndHitsGreaterThanOrderByCreatetimeDesc(String classify, int hits, Pageable pageable);
   public Page<Video> findByClassifyAndCreatetimeGreaterThanOrderByHitsDesc(String classify, long createtime, Pageable pageable);
   public Page<Video> findByCreatetimeGreaterThanOrderByHitsDesc( long createtime, Pageable pageable);
   Video findByVid(Integer vid);
}
