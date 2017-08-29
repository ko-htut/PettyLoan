package com.yixun.pettyloan.ui.fragment;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.Problem;
import com.yixun.pettyloan.entity.ProblemItemViewBinder;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommonProblemsFragment extends BaseSupportFragment {
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

    public static CommonProblemsFragment getInstance(String title) {
        CommonProblemsFragment sf = new CommonProblemsFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_problems;
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
        mTvTitle.setText("常见问题");
        configRefresh();
    }

    private void bindContent() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Problem.class, new ProblemItemViewBinder());
        mFeedsRecycler.addItemDecoration(new SpaceDecoration((int) getResources().getDimension(R.dimen.goods_margin)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new Problem("1.title", "答：content"));
        items.add(new Problem("2.title", "答：content"));
        items.add(new Problem("3.title", "答：content"));
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