package com.example.bean;

public class Message {
    public static final int RECEIVE_TYPE=1;
    public static final int SEND_TYPE=2;
    private int type;
    private String content;
    public Message(int type,String content){
        this.type=type;
        this.content=content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
