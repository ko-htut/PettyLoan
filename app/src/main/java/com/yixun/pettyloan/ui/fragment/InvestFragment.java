package com.yixun.pettyloan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.MainBannerItemViewBinder;
import com.yixun.pettyloan.entity.ProductItemViewBinder;
import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.presenter.ProductsPresenter;
import com.yixun.pettyloan.rx.base.contract.ProductsContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.ui.widge.SpaceDecorationExceptFirst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class InvestFragment extends MvpBaseFragment<ProductsPresenter> implements ProductsContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_invest_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;
    private String mTitle;
    List<Object> topBannerList;

    public static InvestFragment getInstance(String title) {
        InvestFragment sf = new InvestFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_invest;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToorBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    private void initToorBar() {
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void initView() {
        bindFeeds();
        configRefresh();
    }

    @Override
    protected void initData() {
        items.clear();
        Map<String, Integer> map = new HashMap<>();
        map.put(ProductsListBean.PRO_TYPE, 1);
        mPresenter.getProductsData(map);
        mPresenter.getBannerData(2);
    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(BannerFeed.class, new MainBannerItemViewBinder());
        mFeedAdapter.register(ProductDetailBean.Data.class, new ProductItemViewBinder(this));
        mRecyclerView.addItemDecoration(new SpaceDecorationExceptFirst((int) getResources().getDimension(R.dimen.goods_margin)));
        mRecyclerView.setAdapter(mFeedAdapter);
        topBannerList = new ArrayList<>();
        items = new ArrayList<>();
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
        items.clear();
        Map<String, Integer> map = new HashMap<>();
        map.put(ProductsListBean.PRO_TYPE, 1);
        mPresenter.getProductsData(map);
        mPresenter.getBannerData(2);
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(ProductsListBean productsListBean) {
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
        stateMain();
        List<ProductDetailBean.Data> products = productsListBean.getData().getProductsList();
        for (int i = 0; i < products.size(); i++) {
            items.add(products.get(i));
        }
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTopBannerContent(BannerBean bannerBean) {
        List<BannerBean.Data.Banner> banner = bannerBean.getData().getBannerList();
        topBannerList.clear();
        for (int i = 0; i < banner.size(); i++) {
            topBannerList.add(Apis.HOST + banner.get(i).getBannerUrl());
        }
        items.add(0, new BannerFeed(topBannerList));
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFeedBannerContent(BannerBean bannerBean) {

    }
}