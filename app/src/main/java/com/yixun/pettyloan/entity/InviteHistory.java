package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class InviteHistory {

    public @NonNull
    String time;
    public @NonNull
    String bouns;
    public @NonNull
    int coupon;
    public @NonNull
    int peopleNum;


    public InviteHistory(@NonNull final String time, @NonNull final String bouns, @NonNull final int coupon,@NonNull final int peopleNum) {
        this.time = time;
        this.bouns = bouns;
        this.coupon = coupon;
        this.peopleNum = peopleNum;
    }
}
