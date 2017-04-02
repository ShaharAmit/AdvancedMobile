package com.example.shahar.advancedmobile;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class MyFragmentEvent {
    public static final int SEND_TEXT = 1;
    public static final int SOME_OTHER_COMMAND = 2;
    public static final int SHOW_TOAST = 3;


    private int c;
    private String message;

    public MyFragmentEvent(@FRAG_COMMANDS int c, String message) {
        this.c = c;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCommand() {
        return c;
    }

    /**
     * Session wrapper id
     */
    @Retention(SOURCE)
    @IntDef({
            SEND_TEXT,
            SOME_OTHER_COMMAND,
            SHOW_TOAST,
    })
    public @interface FRAG_COMMANDS {
    }
}
