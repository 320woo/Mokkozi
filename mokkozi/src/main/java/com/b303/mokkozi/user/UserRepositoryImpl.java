package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    QUser qUser = QUser.user;

    @Transactional
    public List<User> getRandomUser(Long userId){
//        List<User> list = jpaQueryFactory.select(qUser).from(qUser).where(qUser.id.)
        return null;
    }

}
