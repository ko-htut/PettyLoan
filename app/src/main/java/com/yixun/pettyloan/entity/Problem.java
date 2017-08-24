package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class Problem {

    public @NonNull
    String title;
    public @NonNull
    String content;


    public Problem(@NonNull final String title,@NonNull final String content) {
        this.title = title;
        this.content = content;
    }
}
