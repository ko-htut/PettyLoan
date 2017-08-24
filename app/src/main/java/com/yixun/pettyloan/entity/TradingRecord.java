package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;

/**
 * Created by zongkaili on 17-8-11.
 */

public class TradingRecord {

    public @NonNull
    String title;
    public @NonNull
    String time;
    public @NonNull
    String amount;


    public TradingRecord(@NonNull final String title, @NonNull final String time,@NonNull final String amount) {
        this.title = title;
        this.time = time;
        this.amount = amount;
    }
}
