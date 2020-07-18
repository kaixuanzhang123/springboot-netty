package com.kaikai.netty.server.config;


import com.kaikai.netty.common.dispatcher.MessageDispatcher;
import com.kaikai.netty.common.dispatcher.MessageHandlerContainer;
import com.kaikai.netty.common.messagehandler.heartbeat.HeartbeatRequestHandler;
import com.kaikai.netty.common.messagehandler.heartbeat.HeartbeatResponseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyServerConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }

    @Bean
    public HeartbeatRequestHandler heartbeatRequestHandler() {
        return new HeartbeatRequestHandler();
    }

    @Bean
    public HeartbeatResponseHandler heartbeatResponseHandler() {
        return new HeartbeatResponseHandler();
    }
}
