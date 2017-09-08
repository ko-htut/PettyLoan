package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.yixun.pettyloan.ui.webview.WebViewActivity;

import butterknife.OnClick;

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


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            cardOne.setOnClickListener(this);
            cardTwo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_feature_one:
                    WebViewActivity.loadUrl(v.getContext(), AppConfig.URL_WEBVIEW_TEST, "加载中...");
                    break;
                case R.id.card_feature_two:
                    WebViewActivity.loadUrl(v.getContext(), AppConfig.URL_WEBVIEW_TEST, "加载中...");
                    break;
                default:
                    break;
            }
        }
    }
}
