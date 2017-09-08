package com.yixun.pettyloan.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.yixun.pettyloan.model.bean.TradeRecordBean.Data.Record;

/**
 * Created by zongkaili on 17-8-11.
 */
public class TradingRecordItemViewBinder extends ItemViewBinder<Record, TradingRecordItemViewBinder.ViewHolder> {
    private Context mContext;

    public TradingRecordItemViewBinder(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_trading_record, parent, false);
        return new ViewHolder(mContext, view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Record record) {
        holder.setData(record);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private
        @NonNull
        final TextView title;
        private
        @NonNull
        final TextView time;
        private
        @NonNull
        final TextView amount;


        ViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            amount = (TextView) itemView.findViewById(R.id.amount);
        }

        public void setData(Record record) {
            title.setText(String.valueOf(record.getRecord_name()));
            time.setText(record.getCreate_time());
            amount.setText(String.valueOf(record.getTrade_money()));
            if (record.getTrade_money() < 0)
                amount.setTextColor(ContextCompat.getColor(context, R.color.text_blue));
            if (record.getTrade_money() > 0)
                amount.setTextColor(ContextCompat.getColor(context, R.color.text_orange));
        }
    }
}
