package com.example.webrtc_signalingserver.Signaling;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

// 기능 : 웹소켓 사용에 필요한 설정
@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, WebSocketConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .withSockJS();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalHandler(), "/signal")
                .setAllowedOrigins("http://localhost:3000")
                .setAllowedOrigins("https://d34w3p8z4etsgt.cloudfront.net")
                .withSockJS(); // allow all origins
    }

    @Bean
    public WebSocketHandler signalHandler() {
        return new SignalHandler();
    }
}
