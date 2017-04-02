package com.example.shahar.advancedmobile;

public class MyEvent {

    private String message;
    private int evNum;

    public MyEvent(String message, int evNum) {
        this.message = message;
        this.evNum = evNum;
    }

    public String getMessage() {
        return message;
    }
    public int getEvNum(){ return evNum; }

}
