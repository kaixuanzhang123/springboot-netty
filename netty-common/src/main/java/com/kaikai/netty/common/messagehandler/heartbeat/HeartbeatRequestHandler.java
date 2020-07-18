package com.kaikai.netty.common.messagehandler.heartbeat;


import com.kaikai.netty.common.codec.Invocation;
import com.kaikai.netty.common.dispatcher.MessageHandler;
import com.kaikai.netty.common.message.heartbeat.HeartbeatRequest;
import com.kaikai.netty.common.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        logger.info("[execute][收到连接({}) 的心跳请求]", channel.id());
        // 响应心跳
        HeartbeatResponse response = new HeartbeatResponse("{heart:beat}");
        channel.writeAndFlush(new Invocation(HeartbeatResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return HeartbeatRequest.TYPE;
    }

}
