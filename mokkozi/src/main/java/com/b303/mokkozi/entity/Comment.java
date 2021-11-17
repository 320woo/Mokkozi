package com.b303.mokkozi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity{

    private String content;
    private Date regDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_id",nullable = false)
    private Board board;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @PrePersist
    public void createdAt() {
        this.regDate = new Date();
    }

}
