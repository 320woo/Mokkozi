package com.b303.mokkozi.chat;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.ChatMessage;
import com.b303.mokkozi.entity.ChatRoom;
import com.b303.mokkozi.entity.ChatRoomJoin;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meet/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    // 채팅방 목록 조회
    @GetMapping("/me")
    @ApiOperation(value = "채팅방 목록 조회", notes = "내가 참여한 채팅방 목록을 조회한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<Optional<List<ChatRoomJoin>>> getChatRoomList() {
        // (작성중) jwt 토큰 값을  파라미터로 받아와서 인증 및 해당 유저의 id를 확인
        Long id = null;


        // 해당 id를 기준으로 채팅방 목록을 조회한다
        // Optional<List<ChatRoomJoin>> chatRooms = chatService.searchChatRoomList(id);

        return new ResponseEntity<>(chatService.searchChatRoomList(id), HttpStatus.OK);
    }

    // 해당 채팅방의 메시지 목록을 불러오기
    @GetMapping("/me/{chat_room_id}")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> getChatMsgList(@PathVariable String chat_room_id) {

        // 해당 채팅방 id를 기준으로 메시지를 탐색해서 리턴
        return null;
    }

    // 채팅 메시지 전송
    @MessageMapping("/send/{chat_room_id}")
    @SendTo("/topic/{chat_room_id}")
    public ChatMessage sendMsg() {
        return null;
    }

    // chat (delete) - 채팅방 삭제(나가기)

    // chat (post) - 채팅방 만들기
}
