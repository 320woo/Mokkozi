package com.b303.mokkozi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class UserFollow extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_follower")
    private User userFollower;

    @ManyToOne
    @JoinColumn(name="user_following")
    private User userFollowing;

}
