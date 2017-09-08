package com.yixun.pettyloan.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.Commodity;
import com.yixun.pettyloan.entity.CommodityItemViewBinder;
import com.yixun.pettyloan.entity.MyCreditsHeader;
import com.yixun.pettyloan.entity.MyCreditsHeaderViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.SpaceDecorationExceptFirst;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCreditsFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.recycler)
    RecyclerView mFeedsRecycler;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static MyCreditsFragment getInstance(String title) {
        MyCreditsFragment sf = new MyCreditsFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_credits;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        bindContent();
    }

    @Override
    protected void initData() {
        configRefresh();
    }

    private void initToolbar() {
        mTvTitle.setText(mTitle);
    }

    private void bindContent() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(MyCreditsHeader.class, new MyCreditsHeaderViewBinder(context));
        mFeedAdapter.register(Commodity.class, new CommodityItemViewBinder(context));
        mFeedsRecycler.addItemDecoration(new SpaceDecorationExceptFirst((int) getResources().getDimension(R.dimen.goods_margin)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new MyCreditsHeader(getResources().getString(R.string.credits_pettyloan_credits)));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "大容量静音家用空气加湿器"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "家用台式易安装小空间洗碗机"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "家用小电器保湿壶"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "大容量静音家用空气加湿器"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "家用台式易安装小空间洗碗机"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "家用小电器保湿壶"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "大容量静音家用空气加湿器"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "家用台式易安装小空间洗碗机"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "家用小电器保湿壶"));
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

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            default:
                break;
        }
    }
}