package com.pili.syang.service.impl;

import com.pili.syang.entity.User;
import com.pili.syang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements com.pili.syang.service.UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param user
     * @return null:账号不存在或者密码错误 user:带id得成功
     */
    @Override
    public User login(User user) {
        User user1=userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
        if (user1!=null){
            //获取token并存入数据库
            user1.setToken(UUID.randomUUID().toString());
            userRepository.save(user1);
            return user1;
        }
        return null;
    }

    /**
     *
     * @param user
     * @return 1:注册成功 0:email已被占用
     */
    @Override
    public int regsiter(User user) {
        User user1=userRepository.findByEmail(user.getEmail());
        if (user1!=null){
            return 0;
        }
        userRepository.save(user);
        return 1;
    }

    @Override
    public List<User> ADallUser() {
        return userRepository.findAll();
    }

    @Override
    public User queryByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public User updataUser(User user) {
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User findOne(Integer uid) {
        User byUid = userRepository.findByUid(uid);
        return byUid;
    }

}
