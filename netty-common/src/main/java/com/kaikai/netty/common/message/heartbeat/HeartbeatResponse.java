package com.kaikai.netty.common.message.heartbeat;


import com.kaikai.netty.common.dispatcher.Message;

/**
 * 消息 - 心跳响应
 */
public class HeartbeatResponse implements Message {

    /**
     * 类型 - 心跳响应
     */
    public static final String TYPE = "HEARTBEAT_RESPONSE";

    public String body ;

    public HeartbeatResponse(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HeartbeatResponse{}";
    }

}
