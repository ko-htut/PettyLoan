package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.mode.GoodsMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaolei on 17-8-9.
 */

public class GoodsFragment extends Fragment {
    @BindView(R.id.recycle_goods_list)
    RecyclerView mRecycleView;

    private List<GoodsMode> mGoodsList;

    public static GoodsFragment newInstance(String type) {
        GoodsFragment fr = new GoodsFragment();
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goods, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGoodsList = new ArrayList<>();
        GoodsMode mode1 = new GoodsMode();
        mode1.setGoodsName("产品1");
        mode1.setBigRate("10");
        mode1.setLittleRate("% + 0.32%*");
        GoodsMode mode2 = new GoodsMode();
        mode2.setGoodsName("产品2");
        mode2.setBigRate("5");
        mode2.setLittleRate("% + 0.32%*");
        GoodsMode mode3 = new GoodsMode();
        mode3.setGoodsName("产品3");
        mode3.setBigRate("10");
        mode3.setLittleRate("%");
        mGoodsList.add(mode1);
        mGoodsList.add(mode2);
        mGoodsList.add(mode3);
        RecycleAdapter adapter = new RecycleAdapter(getActivity(), mGoodsList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.addItemDecoration(new SpaceDecoration((int) getResources().getDimension(R.dimen.goods_margin)));
        mRecycleView.setAdapter(adapter);
    }

    private class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<GoodsMode> mList;
        private Context mContext;

        public RecycleAdapter(Context context, List<GoodsMode> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods, null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            GoodsMode goods = mList.get(position);
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.mGoodsNameTv.setText(goods.getGoodsName());
            viewHolder.mBigRateTv.setText(goods.getBigRate());
            viewHolder.mLittleRateTv.setText(goods.getLittleRate());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mTitleLl;
        private TextView mGoodsNameTv, mBigRateTv, mLittleRateTv, mInvestTv;

        MyViewHolder(View itemView) {
            super(itemView);
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
