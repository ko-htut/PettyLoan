package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by zongkaili on 17-8-11.
 */

public class BannerFeed {

    public @NonNull
    List<Object> urlList;


    public BannerFeed(@NonNull final List<Object> urlList) {
        this.urlList = urlList;
    }
}
