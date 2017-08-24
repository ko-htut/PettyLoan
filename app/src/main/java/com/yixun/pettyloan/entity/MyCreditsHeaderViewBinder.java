package com.yixun.pettyloan.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;

/**
 * Created by zongkaili on 17-8-11.
 */
public class MyCreditsHeaderViewBinder extends ItemViewBinder<MyCreditsHeader, MyCreditsHeaderViewBinder.ViewHolder> {
    private Context mContext;
    public MyCreditsHeaderViewBinder(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.fragment_my_credits_header, parent, false);
        return new ViewHolder(mContext,view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MyCreditsHeader mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private
        @NonNull
        final ImageView imageView;
        private
        @NonNull
        final TextView credits;
        private
        @NonNull
        final TextView exchange;

        ViewHolder(Context context,@NonNull View itemView) {
            super(itemView);
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.iv_credits_flag);
            credits = (TextView) itemView.findViewById(R.id.tv_my_credits);
            exchange = (TextView) itemView.findViewById(R.id.tv_exchange);
        }

        public void setData(MyCreditsHeader mode) {
            credits.setText(mode.credits);
//            Glide.with(context.getApplicationContext())
//                    .load(Integer.valueOf(mode.url))
//                    .crossFade()
//                    .fitCenter()
//                    .into(imageView);
        }
    }
}
