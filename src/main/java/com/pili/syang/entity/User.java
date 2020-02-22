package com.pili.syang.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private String email;
    private String name;
    private String password;
    private String token;
    private String sex;
    private String image;
    private Integer fans;//粉丝数

//    @OneToMany(mappedBy = "author")
////    @JoinColumn(name = "uid",referencedColumnName = "uid")
//    private List<Video> videos=new LinkedList<>();
}
