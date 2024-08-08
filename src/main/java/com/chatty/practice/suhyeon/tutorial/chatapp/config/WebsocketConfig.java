package com.chatty.practice.suhyeon.tutorial.chatapp.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    private final CustomHttpSessionHandshakeInterceptor customHttpSessionHandshakeInterceptor;
    private final CustomMessageLogInterceptor customMessageLogInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:5173")
                    .addInterceptors(customHttpSessionHandshakeInterceptor);
                //.withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        // 외부 메시지 브로커
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setVirtualHost("/")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
    }

    // 매 요청마다 수행
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(customMessageLogInterceptor);
    }

    //핸드쉐이크 요청에 대하여 수행

}
