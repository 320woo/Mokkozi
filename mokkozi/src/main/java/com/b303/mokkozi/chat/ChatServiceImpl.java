package com.b303.mokkozi.chat;

import com.b303.mokkozi.chat.dto.ChatMessageDto;
import com.b303.mokkozi.chat.request.ChatMsgListGetReq;
import com.b303.mokkozi.entity.ChatMessage;
import com.b303.mokkozi.entity.ChatRoom;
import com.b303.mokkozi.entity.ChatRoomJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public Optional<List<ChatRoomJoin>> getChatRoomList(Long userId) {

        // 해당 유저의 채팅방 목록을 select 한 Optional 객체를 리턴
        return chatRepository.findByUserId(userId);
    }

    @Override
    public Page<ChatMessageDto> getChatMsgList(Long chatRoomId, ChatMsgListGetReq chatMsgListGetReq) {
        // 작성 필요
        return null;
    }

    @Override
    public void insertMessage(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

}
