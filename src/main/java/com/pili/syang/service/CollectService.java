package com.pili.syang.service;

import com.pili.syang.entity.Collect;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CollectService {
    void addCollect(Collect collect);
    void decCollect(Collect collect);
    boolean checkCollect(Collect collect);
    Page<Collect> allCollect(Integer uid, Integer num);
}
