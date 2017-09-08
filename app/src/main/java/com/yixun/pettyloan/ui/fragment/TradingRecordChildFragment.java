package com.yixun.pettyloan.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.TradingRecord;
import com.yixun.pettyloan.entity.TradingRecordItemViewBinder;
import com.yixun.pettyloan.model.bean.TradeRecordBean;
import com.yixun.pettyloan.model.bean.TradeRecordBean.Data.Record;
import com.yixun.pettyloan.presenter.TradeRecordPresenter;
import com.yixun.pettyloan.rx.base.contract.TradeRecordContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class TradingRecordChildFragment extends MvpBaseFragment<TradeRecordPresenter> implements TradeRecordContract.View {
    private String mTitle;
    private int mType;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.recycler)
    RecyclerView mFeedsRecycler;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static TradingRecordChildFragment getInstance(String title, int type) {
        TradingRecordChildFragment sf = new TradingRecordChildFragment();
        sf.mTitle = title;
        sf.mType = type;
        return sf;
    }


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
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
//        bindContentTest();
        configRefresh();
        initFeed();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (mFeedAdapter.getItemCount() > 0)
            return;

        loadData();
    }

    private void loadData() {
        Map<String, Object> map = new HashMap<>();
        map.put(TradeRecordBean.PAGE_NUM, 0);
        map.put(TradeRecordBean.PAGE_SIZE, 10);
        map.put(TradeRecordBean.TRADE_TYPE, mType);
        mPresenter.getTradeRecord(map);
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
        loadData();
    }

    private void initFeed() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Record.class, new TradingRecordItemViewBinder(context));
        mFeedsRecycler.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
    }

    @Override
    public void showContent(TradeRecordBean bean) {
        if (mRefresh.isRefreshing())
            mRefresh.setRefreshing(false);
        if (!bean.isSuccess()) {
            showErrorMsg(bean.getMsg());
            return;
        }
        bindContent(bean.getData().getTradeRecords());
    }

    private void bindContentTest() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Record.class, new TradingRecordItemViewBinder(context));
        mFeedsRecycler.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.line_height)));
        mFeedsRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new TradingRecord("团团赚投资", "2016-09-09 15：23：33", "-10,000.00"));
        items.add(new TradingRecord("充值", "2016-09-10 15：29：44", "+10,000.00"));
        items.add(new TradingRecord("团团赚转让", "2016-09-04 16：13：12", "-10,000.00"));
        items.add(new TradingRecord("团团赚投资", "2016-09-09 15：23：33", "-10,000.00"));
        items.add(new TradingRecord("充值", "2016-09-10 15：29：44", "+10,000.00"));
        items.add(new TradingRecord("团团赚转让", "2016-09-04 16：13：12", "-10,000.00"));
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    private void bindContent(List<Record> records) {
        if (records == null || records.isEmpty()) {
            if (isVisible()) {
                showErrorMsg("数据为空");
            }
            return;
        }
        mFeedAdapter.setItems(records);
        mFeedAdapter.notifyDataSetChanged();
    }
}