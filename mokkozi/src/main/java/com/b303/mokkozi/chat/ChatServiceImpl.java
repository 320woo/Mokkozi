package com.b303.mokkozi.chat;

import com.b303.mokkozi.entity.ChatRoom;
import com.b303.mokkozi.entity.ChatRoomJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Optional<List<ChatRoomJoin>> searchChatRoomList(Long userId) {

        // 해당 유저의 채팅방 목록을 select 한 Optional 객체를 리턴
        return chatRepository.findByUserId(userId);
    }
}
