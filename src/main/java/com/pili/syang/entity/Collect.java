package com.pili.syang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer colid;

    private Integer uid;

    @ManyToOne(targetEntity = Video.class)
    @JoinColumn(name = "vid",referencedColumnName = "vid")
    private  Video vid;
}
