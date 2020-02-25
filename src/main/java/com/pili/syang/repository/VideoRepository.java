package com.pili.syang.repository;

import com.pili.syang.entity.User;
import com.pili.syang.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
   public Page<Video> findByClassifyAndHitsGreaterThanOrderByCreatetimeDesc(String classify, int hits, Pageable pageable);
   public Page<Video> findByClassifyAndCreatetimeGreaterThanOrderByHitsDesc(String classify, long createtime, Pageable pageable);
   public Page<Video> findByCreatetimeGreaterThanOrderByHitsDesc( long createtime, Pageable pageable);
   Video findByVid(Integer vid);
   public  Page<Video> findByAuthorInOrderByCreatetimeDesc(List<User> ups,Pageable pageable);
   Page<Video> findByAuthorOrderByCreatetimeDesc(User user,Pageable pageable);
   void deleteByVid(Integer vid);
   @Query(value = "SELECT sum(hits),sum(collect),sum(replies) from video where uid=:author", nativeQuery = true)
   Object[] findSumToVideo(@Param("author") Integer author);

   Integer countByClassifyAndCreatetimeGreaterThan(String classify,long createtime);
}
