package com.yixun.pettyloan.entity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.yixun.pettyloan.model.bean.Notice;

/**
 * Created by zongkaili on 17-8-11.
 */
public class MessageItemViewBinder extends ItemViewBinder<Notice, MessageItemViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_notice_message, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Notice mode) {
        holder.title.setText(mode.getNoticeTitle());
        holder.content.setText(mode.getNoticeContent());
        holder.time.setText(mode.getNoticeTime());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        final TextView title;
        private @NonNull
        final TextView content;
        private @NonNull
        final TextView time;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
