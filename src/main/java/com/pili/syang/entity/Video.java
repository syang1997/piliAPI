package com.pili.syang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vid;

    private String vname;
    private String cover;//封面
    private String vurl;//视频连接
    private boolean original;//原创
    private String classify;//分区
    private String intro;//简介
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uid",referencedColumnName = "uid")
    private User author;//作者id user中的uid
    private Integer collect;//收藏数
    private Integer hits;//点击
    private long createtime;//创建时间
    private String duration;//时长
    private Integer replies;//回复数
}
