package com.pili.syang.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Revert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    private String msg;//内容

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "superior",referencedColumnName = "cid")
    private Comment superior; //一级评论
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "acceptor",referencedColumnName = "uid")
    private User acceptor;//该二级级评论的接受方

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "publisher",referencedColumnName = "uid")
    private User publisher;// 该条评论的发布者
    private long createtime;//创建时间
    private Integer praise; //点赞数
    private Integer replies;//回复数
    private Integer statu;

}
