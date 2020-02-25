package com.pili.syang.repository;

import com.pili.syang.entity.Revert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevertRepository extends JpaRepository<Revert,Integer> {
    Revert findByRid(Integer rid);

}
