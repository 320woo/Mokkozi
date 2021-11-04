package com.b303.mokkozi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class User extends BaseEntity{

    @Column(nullable = false)
    private String email;

    @JsonIgnore // JSON 형식으로 해당 객체를 전달할 때, 이 필드는 제외된다.
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String gender;

    private Date birth;

    private String address;

    @Column(nullable = false)
    private String active; // 대기, 활동, 탈퇴, 정지, 거부

    @Column(nullable = false)
    private Long type; // 0: 관리자, 1: 사용자

    private String profile; // 프로필 사진 url

    private Long penaltyCount;

}
