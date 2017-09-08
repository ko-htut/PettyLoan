package com.yixun.pettyloan.entity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.ui.fragment.AboutFragment;
import com.yixun.pettyloan.ui.webview.WebViewActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zongkaili on 17-8-11.
 */
public class FeatureTwoItemViewBinder extends ItemViewBinder<FeatureTwo, FeatureTwoItemViewBinder.ViewHolder> {
    private Context mContext;

    public FeatureTwoItemViewBinder(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_home_feed_feature_two, parent, false);
        return new ViewHolder(mContext,view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull FeatureTwo feature) {
        holder.setData(feature);
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private @NonNull Context context;
        private
        @NonNull
        CardView cardOne, cardTwo;
        private
        @NonNull
        ImageView imgOne, imgTwo;
        private
        @NonNull
        TextView oneTitle, oneTitleSub, twoTitle, twoTitleSub;


        ViewHolder(Context context , @NonNull View itemView) {
            super(itemView);
            this.context = context;
            cardOne = (CardView) itemView.findViewById(R.id.card_feature_one);
            imgOne = (ImageView) itemView.findViewById(R.id.iv_feature_one);
            oneTitle = (TextView) itemView.findViewById(R.id.tv_feature_one);
            oneTitleSub = (TextView) itemView.findViewById(R.id.tv_feature_one_sub);
            cardTwo = (CardView) itemView.findViewById(R.id.card_feature_two);
            imgTwo = (ImageView) itemView.findViewById(R.id.iv_feature_two);
            twoTitle = (TextView) itemView.findViewById(R.id.tv_feature_two);
            twoTitleSub = (TextView) itemView.findViewById(R.id.tv_feature_two_sub);
        }

        void setData(@NonNull final FeatureTwo feature) {
            oneTitle.setText(feature.titleOne);
            if (TextUtils.isEmpty(feature.subTitleOne)) {
                oneTitleSub.setVisibility(View.GONE);
            } else {
                oneTitleSub.setText(feature.subTitleOne);
            }
            imgOne.setImageResource(feature.imgOne);
            twoTitle.setText(feature.titleTwo);
            if (TextUtils.isEmpty(feature.subTitleTwo)) {
                twoTitleSub.setVisibility(View.GONE);
            } else {
                twoTitleSub.setText(feature.subTitleTwo);
            }
            imgTwo.setImageResource(feature.imgTwo);
            cardOne.setOnClickListener(this);
            cardTwo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_feature_one:
                    EventBus.getDefault().post(new StartBrotherEvent(AboutFragment.getInstance()));
                    break;
                case R.id.card_feature_two:
                    WebViewActivity.loadUrl(context, AppConfig.URL_WEBVIEW_TEST, "加载中...");
                    break;
                default:
                    break;
            }
        }
    }
}
