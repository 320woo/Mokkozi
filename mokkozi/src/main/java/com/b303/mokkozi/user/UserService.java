package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserFollow;
import com.b303.mokkozi.user.dto.UserFollowDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> findByEmail(String email);

    void createFollow(User fromUser, String toUserEmail);

    void deleteFollow(Long Id);

    List<UserFollowDto> getFollowers(User user);

    List<UserFollowDto> getFollowing(User user);
}
