package com.pili.syang.service.impl;

import com.pili.syang.entity.Concern;
import com.pili.syang.entity.User;
import com.pili.syang.repository.ConcernRepository;
import com.pili.syang.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConcernServiceImpl implements ConcernService {

    @Autowired
    private ConcernRepository concernRepository;

    @Override
    public void addConcern(Concern concern) {
        concernRepository.save(concern);
    }

    @Transactional
    @Override
    public void delConcern(Concern concern) {
        concernRepository.deleteByFansAndUp(concern.getFans(),concern.getUp());
    }

    @Override
    public List<Concern> getAllConcern(Integer uid) {
        return concernRepository.findByFans(uid);
    }
    public boolean chackCoern(Integer fans, User up){
        List<Concern> byFansAndUp = concernRepository.findByFansAndUp(fans, up);
        if (byFansAndUp.isEmpty()){
            return false;
        }
        return true;
    }
}
