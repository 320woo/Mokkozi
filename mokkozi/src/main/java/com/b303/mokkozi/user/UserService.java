package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.user.request.JoinInfoPostReq;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByEmail(String email);

    public User join(JoinInfoPostReq info);
}
