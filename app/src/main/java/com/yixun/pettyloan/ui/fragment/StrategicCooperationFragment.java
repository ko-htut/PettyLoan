package com.yixun.pettyloan.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.CooperateComItemViewBinder;
import com.yixun.pettyloan.entity.CooperateCompany;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StrategicCooperationFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.rv_recycler)
    RecyclerView mRecyclerView;
    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static StrategicCooperationFragment getInstance(String title) {
        StrategicCooperationFragment sf = new StrategicCooperationFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initExpandableLayout();
    }

    @Override
    protected void initData() {
        bindFeeds();
        configRefresh();
    }

    private void initExpandableLayout() {

    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(CooperateCompany.class, new CooperateComItemViewBinder(context));
        mRecyclerView.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mRecyclerView.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new CooperateCompany(String.valueOf(R.drawable.pic_spd_bank), getString(R.string.large_text)));
        items.add(new CooperateCompany(String.valueOf(R.drawable.pic_hf_bank), getString(R.string.large_text)));
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    private void configRefresh() {
        mRefresh.setColorSchemeColors(ContextCompat.getColor(context, R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus() {
        mRefresh.setRefreshing(false);
    }
}