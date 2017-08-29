package com.yixun.pettyloan.entity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kelly.expandablelayout.ExpandableLayout;
import com.kelly.expandablelayout.ExpandableLayoutListenerAdapter;
import com.kelly.expandablelayout.ExpandableLinearLayout;
import com.kelly.expandablelayout.Utils;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;

/**
 * Created by zongkaili on 17-8-25.
 */
public class CooperateComItemViewBinder extends ItemViewBinder<CooperateCompany, CooperateComItemViewBinder.ViewHolder> {
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public CooperateComItemViewBinder(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_cooperate_com, parent, false);
        return new ViewHolder(context, view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CooperateCompany mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private @NonNull ImageView ivLogo;
        private
        @NonNull
        TextView tvIntro;

        ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tvIntro = (TextView) itemView.findViewById(R.id.tv_introduction);
        }


        void setData(@NonNull final CooperateCompany mode) {
            tvIntro.setText(mode.introduction);
            Glide.with(context)
                    .load(Integer.valueOf(mode.url))
                    .into(ivLogo);
        }
    }
}
