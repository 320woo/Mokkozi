package com.b303.mokkozi.user;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserFollow;
import com.b303.mokkozi.user.dto.UserFollowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
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
