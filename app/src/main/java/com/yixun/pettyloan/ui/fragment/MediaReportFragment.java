package com.yixun.pettyloan.ui.fragment;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.AboutBannerItemViewBinder;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.Commodity;
import com.yixun.pettyloan.entity.NewsItemViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MediaReportFragment extends BaseSupportFragment {
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