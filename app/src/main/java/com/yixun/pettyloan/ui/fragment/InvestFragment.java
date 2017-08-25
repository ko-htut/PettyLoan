package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.Product;
import com.yixun.pettyloan.entity.ProductItemViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.SpaceDecoration;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class InvestFragment extends BaseSupportFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_invest_content)
    RecyclerView mRecyclerView;
    @BindView(R.id.banner_invest)
    Banner mBanner;
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
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.blue_medium);
        bindBanner();
    }

    private void bindBanner() {
        mBanner.setImageLoader(new GlideImageLoader());
        List<String> mUrlList = new ArrayList<>();
        mUrlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=c973f24fda4b0e74d9d233efd0a34dd3&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201608%2F10%2F20160810204650_8sQuF.jpeg");
        mUrlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364364886&di=48c237b56ad5126d60aac44b6d35c6ac&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D1038163141%2C2794573076%26fm%3D214%26gp%3D0.jpg");
        mUrlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=a137348f727c523a65936c60d6a6b6cb&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201605%2F26%2F20160526175959_xRWTH.thumb.700_0.jpeg");
        mUrlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=f101d8d3cc8abfed11a2ad6584a30df4&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160907%2FImg467862638.jpg");
        mUrlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345400&di=9812520307a97a517568e690257da493&imgtype=0&src=http%3A%2F%2Fimg.ladywu.net%2F2017%2F0704%2F20170704021437678.jpg");
        mBanner.setImages(mUrlList).start();
    }

    private class GlideImageLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .crossFade()
                    .into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return new ImageView(context);
        }
    }

    @Override
    protected void initData() {
        bindFeeds();
        configRefresh();
    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Product.class, new ProductItemViewBinder(this));
        mRecyclerView.addItemDecoration(new SpaceDecoration((int) getResources().getDimension(R.dimen.goods_margin)));
        mRecyclerView.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new Product("产品１", "30", "% + 0.32%"));
        items.add(new Product("产品2", "40", "% + 0.32%"));
        items.add(new Product("产品3", "50", "% + 0.32%"));
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    private void configRefresh() {
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus() {
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                SystemClock.sleep(1000);
                subscriber.onNext("refresh");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mRefresh.setRefreshing(false);
                    }
                });
    }
}