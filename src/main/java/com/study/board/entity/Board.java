package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data //이것을 지정해야 다른 곳에서 get으로 가져올 수 있음 ! 즉, getter, setter 등을 만들어주는 역할
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) //Auto : 알아서 지정 / Identity : Mysql, MariaDB /Sequence: Oracle
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

}
