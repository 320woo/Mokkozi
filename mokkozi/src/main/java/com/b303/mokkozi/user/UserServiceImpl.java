package com.b303.mokkozi.user;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserFollow;
import com.b303.mokkozi.user.dto.UserFollowDto;
import com.b303.mokkozi.user.request.JoinInfoPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

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
        user.setRole("user");
        user.setActive("대기");
        user.setPenaltyCount(0L);

        // 2. DB에 저장
        User result = userRepository.save(user);
        return result;
    }

    @Override
    public void createFollow(User fromUser, String toUserEmail) {
        User toUser = userRepository.findByEmail(toUserEmail).orElseThrow(()->new NoSuchElementException("not found"));
        userFollowRepository.save(UserFollow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build());
    }

    @Override
    public void deleteFollow(Long Id) {
        UserFollow follow = userFollowRepository.findById(Id).orElseThrow(()->new NoSuchElementException("not found"));
        userFollowRepository.delete(follow);
    }

    @Override
    public List<UserFollowDto> getFollowers(User user) {
        Stream<UserFollow> followers = userFollowRepository.findAllByToUserId(user.getId());
        List<UserFollowDto> list = (List<UserFollowDto>) followers.map(m->new UserFollowDto(m.getId(),m.getToUser().getId(),m.getToUser().getNickname(),m.getToUser().getProfile()));
//        List<UserFollowDto> followers = list.map(m -> new UserFollowDto());;
        return list;
    }

    @Override
    public List<UserFollowDto> getFollowing(User user) {
        Stream<UserFollow> following = userFollowRepository.findAllByFromUserId(user.getId());
        List<UserFollowDto> list = (List<UserFollowDto>) following.map(m->new UserFollowDto(m.getId(),m.getToUser().getId(),m.getToUser().getNickname(),m.getToUser().getProfile()));
        return list;
    }


}
