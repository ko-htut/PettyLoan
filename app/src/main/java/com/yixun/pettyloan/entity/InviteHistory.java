/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
