package com.b303.mokkozi.user;

import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserFollow;
import com.b303.mokkozi.entity.UserInterest;
import com.b303.mokkozi.user.dto.UserFollowDto;
import com.b303.mokkozi.user.request.JoinInfoPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInterestRepository userInterestRepository;

    @Autowired
    UserFollowRepository userFollowRepository;

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User userUpdate(User user) {
        return userRepository.save(user);
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
        user.setRole(info.getRole());
        user.setActive("대기");
        user.setPenaltyCount(0L);

        // 2. DB에 저장
        User result = userRepository.save(user);
        return result;
    }

    @Override
    public List<UserInterest> createUserInterest(JoinInfoPostReq info, User user) {
        List<UserInterest> result = new ArrayList<>();

        for (String hobby : info.getHobby()) {
            UserInterest userInterest = new UserInterest();
            userInterest.setUser(user);
            userInterest.setInterest(hobby);

            userInterestRepository.save(userInterest);
            result.add(userInterest);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void createFollow(User fromUser, String toUserEmail) {
        User toUser = userRepository.findByEmail(toUserEmail).orElseThrow(() -> new NoSuchElementException("not found"));
        if (!userFollowRepository.existsByFromUserIdAndToUserId(fromUser.getId(), toUser.getId()))
            userFollowRepository.save(UserFollow.builder()
                    .fromUser(fromUser)
                    .toUser(toUser)
                    .build());
    }

    @Override
    public void deleteFollow(Long Id) {
        UserFollow follow = userFollowRepository.findById(Id).orElseThrow(() -> new NoSuchElementException("not found"));
        userFollowRepository.delete(follow);
    }

    @Transactional
    @Override
    public List<UserFollowDto> getFollowers(User user) {

        int page = 0;
        int size = userFollowRepository.countByToUserId(user.getId());
        size = size <= 0 ? 1 : size;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<UserFollow> pageTuts = userFollowRepository.findByToUserId(pageable,user.getId());
        Page<UserFollowDto> followerList = pageTuts.map(m -> new UserFollowDto(m.getId(), m.getFromUser().getId(), m.getFromUser().getNickname(), m.getFromUser().getProfile()));

        return followerList.getContent();

    }

    @Override
    public List<UserFollowDto> getFollowing(User user) {

        int page = 0;
        int size = userFollowRepository.countByFromUserId(user.getId());
        size = size <= 0 ? 1 : size;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<UserFollow> pageTuts = userFollowRepository.findByFromUserId(pageable,user.getId());
        Page<UserFollowDto> followerList = pageTuts.map(m -> new UserFollowDto(m.getId(), m.getToUser().getId(), m.getToUser().getNickname(), m.getToUser().getProfile()));

        return followerList.getContent();

    }

    @Override
    public List<User> getRandomUser(User user) {
        List<User> list = userRepositoryImpl.getRandomUser(user.getId());
        return list;
    }


}