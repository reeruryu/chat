package org.example.chat.config;

import lombok.RequiredArgsConstructor;
import org.example.chat.config.properties.RabbitMQProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    private final RabbitMQProperties rabbitMQProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
                .setAllowedOriginPatterns("*") //안해도 무관
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setPathMatcher(new AntPathMatcher("."));  // url을 chat/room/3 -> chat.room.3으로 참조하기 위한 설정
        registry.setApplicationDestinationPrefixes("/pub");
        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue")
                .setRelayHost(rabbitMQProperties.getHost())  // RabbitMQ 서버 호스트
                .setRelayPort(rabbitMQProperties.getStompPort())        // RabbitMQ STOMP 포트
                .setClientLogin(rabbitMQProperties.getUsername())  // RabbitMQ 사용자명
                .setClientPasscode(rabbitMQProperties.getPassword())  // RabbitMQ 비밀번호
                .setSystemLogin(rabbitMQProperties.getUsername())
                .setSystemPasscode(rabbitMQProperties.getPassword());
    }

}
