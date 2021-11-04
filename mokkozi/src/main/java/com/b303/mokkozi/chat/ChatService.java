package com.b303.mokkozi.chat;

import com.b303.mokkozi.entity.ChatRoomJoin;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    Optional<List<ChatRoomJoin>> searchChatRoomList(Long userId);
}
