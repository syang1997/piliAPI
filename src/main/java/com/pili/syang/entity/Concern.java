package com.pili.syang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coid;
    /**
     * 关注表 多对多关系;
     */

    private Integer fans;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "up",referencedColumnName = "uid")
    private User up;

}
