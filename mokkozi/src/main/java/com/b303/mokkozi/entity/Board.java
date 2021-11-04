package com.b303.mokkozi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Board extends BaseEntity{

    private String title;
    @Column(columnDefinition = "BLOB")
    private String content;
    private Date regDate;
    private String active; //활동, 정지

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void createdAt() {
        this.regDate = new Date();
    }

    @OneToMany(mappedBy = "board")
    private List<UserBoardLike> userBoardLikeList = new ArrayList<UserBoardLike>();

}
