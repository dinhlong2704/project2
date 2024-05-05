package com.example.projectfinal;

public class Message {
    public static final int TYPE_LEFT = 1;
    public static final int TYPE_RIGHT = 2;
    public final String msg;
    public final int msgType;

    public Message(String msg, int type) {
        this.msg = msg;
        this.msgType = type;
    }
}
