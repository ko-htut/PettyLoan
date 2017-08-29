package com.yixun.pettyloan.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.Commodity;
import com.yixun.pettyloan.entity.CommodityItemViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CouponsPackageChildFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.recycler)
    RecyclerView mFeedsRecycler;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static CouponsPackageChildFragment getInstance(String title) {
        CouponsPackageChildFragment sf = new CouponsPackageChildFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_child_recycler;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        bindContent();
    }

    @Override
    protected void initData() {
        configRefresh();
    }

    private void bindContent() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Commodity.class, new CommodityItemViewBinder(context));
        mFeedsRecycler.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>(); items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "大容量静音家用空气加湿器"));
        items = new ArrayList<>(); items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "大容量静音家用空气加湿器"));
        items = new ArrayList<>(); items.add(new Commodity(String.valueOf(R.drawable.pic_credits_flag), "大容量静音家用空气加湿器"));
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