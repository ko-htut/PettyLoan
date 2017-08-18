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
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;

/**
 * Created by zongkaili on 17-8-11.
 */
public class FeatureOneItemViewBinder extends ItemViewBinder<FeatureOne, FeatureOneItemViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_home_feed_feature_one, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull FeatureOne feature) {
        holder.setData(feature);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private
        @NonNull
        CardView cardOne, cardTwo;
        private
        @NonNull
        ImageView imgOne, imgTwo;
        private
        @NonNull
        TextView oneTitle, twoTitle;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOne = (CardView) itemView.findViewById(R.id.card_feature_one);
            imgOne = (ImageView) itemView.findViewById(R.id.iv_feature_one);
            oneTitle = (TextView) itemView.findViewById(R.id.tv_feature_one);
            cardTwo = (CardView) itemView.findViewById(R.id.card_feature_two);
            imgTwo = (ImageView) itemView.findViewById(R.id.iv_feature_two);
            twoTitle = (TextView) itemView.findViewById(R.id.tv_feature_two);
        }

        void setData(@NonNull final FeatureOne feature) {
            oneTitle.setText(feature.titleOne);
            imgOne.setImageResource(feature.imgOne);
            twoTitle.setText(feature.titleTwo);
            imgTwo.setImageResource(feature.imgTwo);
        }
    }
}
