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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.yixun.pettyloan.mode.GoodsMode;

/**
 * Created by zongkaili on 17-8-11.
 */
public class ProductItemViewBinder extends ItemViewBinder<GoodsMode, ProductItemViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_goods, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GoodsMode mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        LinearLayout titleLl;
        private @NonNull
        TextView goodsNameTv, bigRateTv, littleRateTv, investTv;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLl = (LinearLayout) itemView.findViewById(R.id.ll_card_title);
            goodsNameTv = (TextView) itemView.findViewById(R.id.tv_card_goods_name);
            bigRateTv = (TextView) itemView.findViewById(R.id.tv_card_big_rate);
            littleRateTv = (TextView) itemView.findViewById(R.id.tv_card_little_rate);
            investTv = (TextView) itemView.findViewById(R.id.tv_card_invest);
        }


        void setData(@NonNull final GoodsMode mode) {
            goodsNameTv.setText(mode.getGoodsName());
            bigRateTv.setText(mode.getBigRate());
            littleRateTv.setText(mode.getLittleRate());
        }
    }
}
