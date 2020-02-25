package com.pili.syang.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String msg;//内容
    private Integer vid; //视频的id

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "acceptor",referencedColumnName = "uid")
    private User acceptor;//该一级评论的接受方 也就是视频的作者

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "publisher",referencedColumnName = "uid")
    private User publisher;// 该条评论的发布者
    private long createtime;//创建时间
    private Integer praise; //点赞数
    private Integer replies;//回复数
    private Integer statu;//状态 0 未读 1 已读

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "superior")
    private List<Revert> sends=new LinkedList<>();
    /*
    一级评论直接对视频
     */
}
