package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.ui.fragment.LoginFragment;
import com.yixun.pettyloan.ui.fragment.PaymentFragment;
import com.yixun.pettyloan.ui.fragment.ProductDetailFragment;
import com.yixun.pettyloan.utils.PreferenceUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zongkaili on 17-8-11.
 */
public class ProductItemViewBinder extends ItemViewBinder<ProductDetailBean.Data, ProductItemViewBinder.ViewHolder> {
    private Fragment fragment;

    public ProductItemViewBinder(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ProductDetailBean.Data mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private
        @NonNull
        CardView rootView;
        private
        @NonNull
        LinearLayout titleLl;
        private
        @NonNull
        TextView goodsNameTv, bigRateTv, littleRateTv, investTv;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = (CardView) itemView.findViewById(R.id.rootView);
            titleLl = (LinearLayout) itemView.findViewById(R.id.ll_card_title);
            goodsNameTv = (TextView) itemView.findViewById(R.id.tv_card_goods_name);
            bigRateTv = (TextView) itemView.findViewById(R.id.tv_card_big_rate);
            littleRateTv = (TextView) itemView.findViewById(R.id.tv_card_little_rate);
            investTv = (TextView) itemView.findViewById(R.id.tv_bottom_invest);
        }


        void setData(@NonNull final ProductDetailBean.Data mode) {
            goodsNameTv.setText(mode.getPro_name());
            bigRateTv.setText(String.valueOf(mode.getRate()));
            littleRateTv.setText("% +0.32%");
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new StartBrotherEvent(ProductDetailFragment.getInstance(mode)));
                }
            });
            investTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_LOGGED, false)) {
                        EventBus.getDefault().post(new StartBrotherEvent(PaymentFragment.getInstance(mode)));
                    } else {
                        EventBus.getDefault().post(new StartBrotherEvent(LoginFragment.newInstance()));
                    }
                }
            });
        }

    }
}
