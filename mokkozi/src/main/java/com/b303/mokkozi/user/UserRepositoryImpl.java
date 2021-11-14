package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.QUser;
import com.b303.mokkozi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private EntityManager em;

    QUser qUser = QUser.user;

    @Transactional
    public List<User> getRandomUser(Long userId){
//        List<User> list = jpaQueryFactory.select(qUser).from(qUser).where(qUser.id.ne(userId)).orderBy();
        int maxResults = 5;
        String str = "SELECT u FROM User u WHERE u.id is not "+ userId +" ORDER BY RAND()";
        return Optional.ofNullable(em.createQuery(str).setMaxResults(maxResults)
                .getResultList()).orElse(null);
    }

}
