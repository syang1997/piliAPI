package com.pili.syang.service.impl;

import com.pili.syang.entity.Collect;
import com.pili.syang.repository.CollectRepository;
import com.pili.syang.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectSericeImpl implements CollectService {

    @Autowired
    private CollectRepository collectRepository;

    @Override
    public void addCollect(Collect collect) {
        collectRepository.save(collect);
    }

    @Transactional
    @Override
    public void decCollect(Collect collect) {
        collectRepository.deleteByUidAndVid(collect.getUid(),collect.getVid());
    }

    @Override
    public boolean checkCollect(Collect collect) {
        List<Collect> byVidAndVid = collectRepository.findByUidAndVid(collect.getUid(), collect.getVid());
        if (byVidAndVid.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Page<Collect> allCollect(Integer uid, Integer num) {
        Pageable pageable = PageRequest.of(num-1, 10);
        return collectRepository.findByUid(uid,pageable);
    }
}
