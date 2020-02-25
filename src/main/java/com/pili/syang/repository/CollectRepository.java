package com.pili.syang.repository;

import com.pili.syang.entity.Collect;
import com.pili.syang.entity.Concern;
import com.pili.syang.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectRepository extends JpaRepository<Collect,Integer> {
    void deleteByUidAndVid (Integer uid, Video vid);
    List<Collect> findByUidAndVid(Integer uid, Video vid);
    Page<Collect> findByUid(Integer uid, Pageable pageable);
}
