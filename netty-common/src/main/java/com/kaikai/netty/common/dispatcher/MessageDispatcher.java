package com.kaikai.netty.common.dispatcher;


import com.alibaba.fastjson.JSON;
import com.kaikai.netty.common.codec.Invocation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@ChannelHandler.Sharable
@Component
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    private ExecutorService executor = new ThreadPoolExecutor(10,20,200L,TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024)
            , new ThreadPoolExecutor.DiscardOldestPolicy());


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        // 获得 type 对应的 MessageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(invocation.getType());
        // 获得  MessageHandler 处理器 的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // 解析消息
        Message message = JSON.parseObject(invocation.getMessage(), messageClass);
        // 执行逻辑
        executor.submit(() -> {
            // noinspection unchecked
            messageHandler.execute(ctx.channel(), message);
        });
    }

}
