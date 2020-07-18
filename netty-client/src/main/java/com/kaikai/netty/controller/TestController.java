package com.kaikai.netty.controller;


import com.kaikai.netty.client.NettyClient;
import com.kaikai.netty.common.codec.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NettyClient nettyClient;

    @PostMapping("/mock")
    public String mock(@RequestParam("type") String type, @RequestParam("message") String message) {
        // 创建 Invocation 对象
        Invocation invocation = new Invocation(type, message);
        // 发送消息
        nettyClient.send(invocation);
        return "success";
    }

}
