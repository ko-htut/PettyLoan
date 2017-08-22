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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;

/**
 * Created by zongkaili on 17-8-11.
 */
public class InviteHistoryItemViewBinder extends ItemViewBinder<InviteHistory, InviteHistoryItemViewBinder.ViewHolder> {
    private Context mContext;
    public InviteHistoryItemViewBinder(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_invite_history, parent, false);
        return new ViewHolder(mContext,view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull InviteHistory mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private
        @NonNull
        final TextView time;
        private
        @NonNull
        final TextView bouns;
        private
        @NonNull
        final TextView coupon;
        private
        @NonNull
        final TextView peopleNum;


        ViewHolder(Context context,@NonNull View itemView) {
            super(itemView);
            this.context = context;
            time = (TextView) itemView.findViewById(R.id.time);
            bouns = (TextView) itemView.findViewById(R.id.tv_bouns_pre_tax);
            coupon = (TextView) itemView.findViewById(R.id.tv_coupon);
            peopleNum = (TextView) itemView.findViewById(R.id.tv_friends);
        }

        public void setData(InviteHistory mode) {
            time.setText(mode.time);
            bouns.setText(mode.bouns);
            coupon.setText(String.valueOf(mode.coupon));
            peopleNum.setText(String.valueOf(mode.peopleNum));
        }
    }
}
