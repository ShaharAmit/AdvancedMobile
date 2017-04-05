package com.example.shahar.advancedmobile;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class BoardMessage {

    private String message;
    private String timestamp;
    private String creationDate;

    public String getCreationDate() {
        return creationDate;
    }


    BoardMessage() {
        timestamp =  new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        creationDate = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());

    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}