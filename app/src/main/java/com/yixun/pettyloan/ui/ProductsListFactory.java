package com.yixun.pettyloan.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.entity.Product;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.ui.fragment.ProductDetailFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by yaolei on 17-8-11.
 */

public class ProductsListFactory {
    private Context mContext;

    public ProductsListFactory(Context context) {
        mContext = context;
    }

    public View newInstance(List<Product> goodsList) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_goods_list, null);
        RecyclerView mRecycleView = (RecyclerView) v.findViewById(R.id.rv_goods_list);
        RecycleAdapter adapter = new RecycleAdapter(mContext, goodsList);
        mRecycleView.addItemDecoration(new SpaceDecoration((int) mContext.getResources().getDimension(R.dimen.goods_margin)));
        mRecycleView.setAdapter(adapter);
        return v;
    }

    private class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Product> mList;
        private Context mContext;

        RecycleAdapter(Context context, List<Product> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final Product product = mList.get(position);
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.mGoodsNameTv.setText(product.getProductName());
            viewHolder.mBigRateTv.setText(product.getBigRate());
            viewHolder.mLittleRateTv.setText(product.getLittleRate());
            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new StartBrotherEvent(ProductDetailFragment.getInstance(product.getProductName())));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView rootView;
        private LinearLayout mTitleLl;
        private TextView mGoodsNameTv, mBigRateTv, mLittleRateTv, mInvestTv;

        MyViewHolder(View itemView) {
            super(itemView);
            rootView = (CardView) itemView.findViewById(R.id.rootView);
            mTitleLl = (LinearLayout) itemView.findViewById(R.id.ll_card_title);
            mGoodsNameTv = (TextView) itemView.findViewById(R.id.tv_card_goods_name);
            mBigRateTv = (TextView) itemView.findViewById(R.id.tv_card_big_rate);
            mLittleRateTv = (TextView) itemView.findViewById(R.id.tv_card_little_rate);
            mInvestTv = (TextView) itemView.findViewById(R.id.tv_card_invest);
        }
    }

    private class SpaceDecoration extends RecyclerView.ItemDecoration {
        private int mSpace;

        SpaceDecoration(int space) {
            mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;

            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = mSpace;
            } else {
                outRect.top = 0;
            }
        }
    }
}
