package com.yixun.pettyloan.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.MessageItemViewBinder;
import com.yixun.pettyloan.model.bean.Notice;
import com.yixun.pettyloan.model.bean.NoticesListBean;
import com.yixun.pettyloan.presenter.NoticesPresenter;
import com.yixun.pettyloan.rx.base.contract.NoticesContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MyMessageFragment extends MvpBaseFragment<NoticesPresenter> implements NoticesContract.View {
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
    List<Notice> items;

    public static MyMessageFragment getInstance(String title) {
        MyMessageFragment sf = new MyMessageFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_message;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        initFeed();
    }

    @Override
    protected void initData() {
        configRefresh();
        Map<String, Object> map = new HashMap<>();
        map.put(NoticesListBean.PAGE_SIZE, 10);
        mPresenter.getNoticesList(map);
    }

    private void initToolbar() {
        mTvTitle.setText(mTitle);
    }

    private void initFeed() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Notice.class, new MessageItemViewBinder());
        mFeedsRecycler.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
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
        Map<String, Object> map = new HashMap<>();
        map.put(NoticesListBean.PAGE_SIZE, 10);
        mPresenter.getNoticesList(map);
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

    @Override
    public void showContent(NoticesListBean bean) {
        if (mRefresh.isRefreshing())
            mRefresh.setRefreshing(false);
        if (!bean.isSuccess()) {
            showErrorMsg(bean.getMsg());
            return;
        }

        bindContent(bean.getData());
    }

    private void bindContent(NoticesListBean.Data data) {
        items = data.getNoticeList();
        if (items.isEmpty()) {
            showErrorMsg("数据为空");
            return;
        }
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

}