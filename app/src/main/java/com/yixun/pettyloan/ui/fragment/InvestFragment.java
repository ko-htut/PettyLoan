package com.yixun.pettyloan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.MainBannerItemViewBinder;
import com.yixun.pettyloan.entity.Product;
import com.yixun.pettyloan.entity.ProductItemViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.SpaceDecorationExceptFirst;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InvestFragment extends BaseSupportFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_invest_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;
    private String mTitle;

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

    private void initToorBar() {
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        bindFeeds();
        configRefresh();
    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(BannerFeed.class, new MainBannerItemViewBinder());
        mFeedAdapter.register(Product.class, new ProductItemViewBinder(this));
        mRecyclerView.addItemDecoration(new SpaceDecorationExceptFirst((int) getResources().getDimension(R.dimen.goods_margin)));
        mRecyclerView.setAdapter(mFeedAdapter);
        List<Object> urlList = new ArrayList<>();
        urlList.add(R.drawable.pic_invest_banner);
        urlList.add(R.drawable.pic_banner2);
        items = new ArrayList<>();
        items.add(new BannerFeed(urlList));
        items.add(new Product("产品１", "30", "% + 0.32%"));
        items.add(new Product("产品2", "40", "% + 0.32%"));
        items.add(new Product("产品3", "50", "% + 0.32%"));
        items.add(new Product("产品4", "30", "% + 0.32%"));
        items.add(new Product("产品5", "40", "% + 0.32%"));
        items.add(new Product("产品6", "50", "% + 0.32%"));
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    private void configRefresh() {
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_dark));
        mRefresh.setOnRefreshListener(() -> updateRefreshStatus());
    }

    public void updateRefreshStatus() {
//        Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                SystemClock.sleep(1000);
//                subscriber.onNext("refresh");
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        mRefresh.setRefreshing(false);
//                    }
//                });
    }
}