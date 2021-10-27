package com.b303.mokkozi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Board extends BaseEntity{

    private String title;
    @Column(columnDefinition = "BLOB")
    private String content;
    private Date regDate;
    private String active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void createdAt() {
        this.regDate = new Date();
    }

}
