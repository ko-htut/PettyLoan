package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class Message {

    public @NonNull
    String title;
    public @NonNull
    String content;
    public @NonNull
    String time;


    public Message(@NonNull final String title,
                   @NonNull final String content,
                   @NonNull final String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }
}
