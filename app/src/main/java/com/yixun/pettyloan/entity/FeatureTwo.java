package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class FeatureTwo {

    public
    @NonNull
    String titleOne;
    public
    @NonNull
    String subTitleOne;
    public
    @NonNull
    int imgOne;
    public
    @NonNull
    String titleTwo;
    public
    @NonNull
    String subTitleTwo;
    public
    @NonNull
    int imgTwo;


    public FeatureTwo(@NonNull final String titleOne,
                      @NonNull final String subTitleOne,
                      @NonNull final int imgOne,
                      @NonNull final String titleTwo,
                      @NonNull final String subTitleTwo,
                      @NonNull final int imgTwo) {
        this.titleOne = titleOne;
        this.subTitleOne = subTitleOne;
        this.titleTwo = titleTwo;
        this.subTitleTwo = subTitleTwo;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
    }
}
