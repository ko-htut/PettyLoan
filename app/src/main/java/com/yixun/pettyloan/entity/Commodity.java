package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class Commodity {

    public @NonNull
    String url;
    public @NonNull
    String name;

    public Commodity(@NonNull final String url,
                     @NonNull final String name) {
        this.url = url;
        this.name = name;
    }
}
