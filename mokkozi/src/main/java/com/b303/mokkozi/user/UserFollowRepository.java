package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,Long> {

//    List<UserFollow> getByUserFollower(Long id);

    Optional<List<UserFollow>> findAllByToUserId(Long id);

    Stream<UserFollow> findAllByFromUserId(Long id);
}
