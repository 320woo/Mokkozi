package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserInterest;
import com.b303.mokkozi.user.dto.UserFollowDto;
import com.b303.mokkozi.user.request.JoinInfoPostReq;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User userUpdate(User user);

    void createFollow(User fromUser, String toUserEmail);

    void deleteFollow(Long Id);

    List<UserFollowDto> getFollowers(User user);

    List<UserFollowDto> getFollowing(User user);

    List<User> getRandomUser(User user);

    User join(JoinInfoPostReq info);

    List<UserInterest> createUserInterest(JoinInfoPostReq info, User user);

    Optional<User> findById(Long id);
}
