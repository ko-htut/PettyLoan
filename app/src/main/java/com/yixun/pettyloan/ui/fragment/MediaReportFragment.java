package com.yixun.pettyloan.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.AboutBannerItemViewBinder;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.Commodity;
import com.yixun.pettyloan.entity.NewsItemViewBinder;
import com.yixun.pettyloan.model.bean.HotListBean;
import com.yixun.pettyloan.presenter.HotPresenter;
import com.yixun.pettyloan.rx.base.contract.HotContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MediaReportFragment extends MvpBaseFragment<HotPresenter> implements HotContract.View{
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
    protected void initInject() {
        getFragmentComponent().inject(this);
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
        mPresenter.getHotData();
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
//        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"1.hahhahhahah"));
//        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"2.hahhahhahah"));
//        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"3.hahhahhahah"));
//        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"4.hahhahhahah"));
//        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"5.hahhahhahah"));
//        items.add(new Commodity(String.valueOf(R.drawable.pic_banner_media),"6.hahhahhahah"));
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
        mPresenter.getHotData();
    }

    @Override
    public void stateError() {
        super.stateError();
        if(mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(HotListBean hotListBean) {
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
        stateMain();
        List<HotListBean.RecentBean> datas = hotListBean.getRecent();
        for (int i = 0; i < datas.size(); i++) {
            items.add(new Commodity(datas.get(i).getThumbnail(),datas.get(i).getTitle()));
        }
        mFeedAdapter.notifyDataSetChanged();
    }
}