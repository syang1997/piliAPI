package com.pili.syang.service;

import com.pili.syang.entity.Concern;
import com.pili.syang.entity.User;

import java.util.List;

public interface ConcernService {
    void addConcern(Concern concern);
    void delConcern(Concern concern);
    List<Concern> getAllConcern(Integer uid);
    public boolean chackCoern(Integer fans, User up);
}
