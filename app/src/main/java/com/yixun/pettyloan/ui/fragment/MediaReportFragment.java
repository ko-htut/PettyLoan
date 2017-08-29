package com.yixun.pettyloan.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.AboutBannerItemViewBinder;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.Commodity;
import com.yixun.pettyloan.entity.NewsItemViewBinder;
import com.yixun.pettyloan.mvp.contract.UserContract;
import com.yixun.pettyloan.presenter.UserPresenter;
import com.yixun.pettyloan.rx.di.component.DaggerActivityComponent;
import com.yixun.pettyloan.rx.di.module.UserModule;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.base.BaseSupportFragmentTest;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MediaReportFragment extends BaseSupportFragmentTest<UserPresenter> implements UserContract.View{
    private String mTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.rv_recycler)
    RecyclerView mRecyclerView;
    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static MediaReportFragment getInstance(String title) {
        MediaReportFragment sf = new MediaReportFragment();
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
        mFeedAdapter.register(BannerFeed.class, new AboutBannerItemViewBinder());
        mFeedAdapter.register(Commodity.class, new NewsItemViewBinder(context));
        mRecyclerView.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.small_space)));
        mRecyclerView.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        List<Object> urlList = new ArrayList<>();
        urlList.add(R.drawable.pic_banner_media);
        urlList.add(R.drawable.pic_banner_trades);
        items.add(new BannerFeed(urlList));
        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"1.hahhahhahah"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"2.hahhahhahah"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"3.hahhahhahah"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"4.hahhahhahah"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"5.hahhahhahah"));
        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"6.hahhahhahah"));
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    private void configRefresh() {
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_dark));
        mRefresh.setOnRefreshListener(() -> updateRefreshStatus());
    }

    public void updateRefreshStatus() {
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerFragmentComponent
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.requestUsers(true);//打开app时自动加载列表
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        pop();
    }

    @Override
    public void setAdapter(DefaultAdapter adapter) {

    }

    @Override
    public void startLoadMore() {

    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public RxPermissions getRxPermissions() {
        return null;
    }
}