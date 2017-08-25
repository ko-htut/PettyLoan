package com.yixun.pettyloan.entity;

import android.animation.TimeInterpolator;
import android.support.annotation.NonNull;

import com.kelly.expandablelayout.Utils;

/**
 * Created by zongkaili on 17-8-25.
 */

public class ManageTeam {

    public @NonNull
    String name;
    public @NonNull
    String duty;
    public @NonNull
    String introduction;
    public final TimeInterpolator interpolator;


    public ManageTeam(@NonNull final String name, @NonNull final String duty,
                      @NonNull final String introduction) {
        this.name = name;
        this.duty = duty;
        this.introduction = introduction;
        //此处直接指定动画TimeInterpolator
        this.interpolator = Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR);
    }
}
