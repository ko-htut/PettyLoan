package com.yixun.pettyloan.entity;

import android.animation.TimeInterpolator;
import android.support.annotation.NonNull;

import com.kelly.expandablelayout.Utils;

/**
 * Created by zongkaili on 17-8-25.
 */

public class CooperateCompany {

    public @NonNull
    String url;
    public @NonNull
    String introduction;


    public CooperateCompany(@NonNull final String url,
                            @NonNull final String introduction) {
        this.url = url;
        this.introduction = introduction;
    }
}
