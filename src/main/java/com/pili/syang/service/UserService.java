package com.pili.syang.service;

import com.pili.syang.entity.User;

import java.util.List;

public interface UserService {

    public User login(User user);
    public int regsiter(User user);
    public List<User> ADallUser();
    public User queryByToken(String token);
    public User updataUser(User user);
    public User findOne(Integer uid);
    void addFans(Integer uid);
    void decFans(Integer uid);
}
