package com.pili.syang.repository;

import com.pili.syang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
        public User findByEmail(String email);

        public User findByEmailAndPassword(String email,String password);

        public User findByToken(String tkoen);

        public User findByUid(Integer uid);
}
