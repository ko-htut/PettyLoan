package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class FeatureOne {

    public
    @NonNull
    String titleOne;
    public
    @NonNull
    int imgOne;
    public
    @NonNull
    String titleTwo;
    public
    @NonNull
    int imgTwo;


    public FeatureOne(@NonNull final String titleOne,
                      @NonNull final int imgOne,
                      @NonNull final String titleTwo,
                      @NonNull final int imgTwo) {
        this.titleOne = titleOne;
        this.titleTwo = titleTwo;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
    }
}
