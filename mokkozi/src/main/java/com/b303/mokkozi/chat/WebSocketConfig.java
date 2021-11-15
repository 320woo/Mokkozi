package com.b303.mokkozi.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메시지브로커가 지원하는 websocket message handling 을 활성화한다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 간단한 메모리 기반 메시지 브로커가 "/sub"가 앞에 붙은 대상에서 클라이언트로 메시지를 다시 전달할 수 있도록 함.
        config.enableSimpleBroker("/sub"); // subscribe
        // 대상 헤더가 "/pub"으로 시작하는 STOMP 메시지는 @Controller 클래스의 @MessageMapping 메서드로 라우팅된다.
        config.setApplicationDestinationPrefixes("/pub"); // publish
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
