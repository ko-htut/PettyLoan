package com.yixun.pettyloan.ui.fragment;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.ManageTeam;
import com.yixun.pettyloan.entity.ManageTeamItemViewBinder;
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

public class ManagementTeamFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.rv_management_team)
    RecyclerView mRecyclerView;
    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static ManagementTeamFragment getInstance(String title) {
        ManagementTeamFragment sf = new ManagementTeamFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_management_team;
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
        ManageTeamItemViewBinder binder = new ManageTeamItemViewBinder(context);
        mFeedAdapter.register(ManageTeam.class, binder);
        mRecyclerView.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mRecyclerView.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new ManageTeam("任开国", "联合首席执行官，共同创始人", getString(R.string.large_text)));
        items.add(new ManageTeam("郭宇航", "首席业务官", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
        items.add(new ManageTeam("邝旭霞", "首席运营和财务官", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"));
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