package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.user.request.JoinInfoPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User join(JoinInfoPostReq info) {
        // 1. DB에 저장하기 위한 엔티티 객체 생성
        User user = new User();
        user.setEmail(info.getEmail());
        user.setNickname(info.getNickname());
        user.setPassword(info.getPassword());
        user.setAddress(info.getAddress() + " " + info.getExtAddress());
        user.setGender(info.getGender());
        user.setBirth(info.getBirth());
        user.setProfile(info.getProfile());
        user.setType(1L);
        user.setActive("대기");
        user.setPenaltyCount(0L);

        // 2. DB에 저장
        User result = userRepository.save(user);
        return result;
    }
}
