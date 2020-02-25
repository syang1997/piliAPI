package com.pili.syang.repository;

import com.pili.syang.entity.Concern;
import com.pili.syang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcernRepository extends JpaRepository<Concern,Integer> {
    void deleteByFansAndUp(Integer fans,User up);
    List<Concern> findByFans(Integer fans);
    List<Concern> findByFansAndUp(Integer fans, User up);
}
