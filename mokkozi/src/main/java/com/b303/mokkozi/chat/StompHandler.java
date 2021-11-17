package com.b303.mokkozi.chat;

import com.b303.mokkozi.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            log.info("StompHandler : STOMP 연결 완료");
            log.info("StompHandler : 검증할 토큰 : {}", accessor.getFirstNativeHeader("token"));
            if (!tokenProvider.validateToken(accessor.getFirstNativeHeader("token"))) {
                // 유효한 토큰을 받지 못한 경우.
                log.info("StompHandler : 유효한 토큰이 아닙니다.");
                throw new AccessDeniedException("access denied");
            }
        }
        return message;
    }
}
