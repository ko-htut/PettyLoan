package com.yixun.pettyloan.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.entity.AboutBannerItemViewBinder;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.ProductItemViewBinder;
import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.presenter.ProductsPresenter;
import com.yixun.pettyloan.rx.base.contract.ProductsContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.ui.widge.LineDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class EntityChildTwoFragment extends MvpBaseFragment<ProductsPresenter> implements ProductsContract.View {
    private String mTitle;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.rv_recycler)
    RecyclerView mRecyclerView;
    MultiTypeAdapter mFeedAdapter;
    List<Object> items;
    private Map<String, Integer> requestMap;

    List<ProductDetailBean.Data> products;

    public static EntityChildTwoFragment getInstance(String title) {
        EntityChildTwoFragment sf = new EntityChildTwoFragment();
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
        bindFeeds();
        configRefresh();
    }

    @Override
    protected void initData() {
        requestMap = new HashMap<>();
        requestMap.put(ProductsListBean.PRO_CATEGORY_ID, 2);
        requestMap.put(ProductsListBean.PRO_TYPE, 0);
        mPresenter.getProductsData(requestMap);
    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(BannerFeed.class, new AboutBannerItemViewBinder());
        mFeedAdapter.register(ProductDetailBean.Data.class, new ProductItemViewBinder(this));
        mRecyclerView.addItemDecoration(new LineDecoration((int) getResources().getDimension(R.dimen.small_space)));
        mRecyclerView.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
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
        mPresenter.getProductsData(requestMap);
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(ProductsListBean productsListBean) {
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
        stateMain();
        items.clear();
        products = productsListBean.getData().getProductsList();
        for (int i = 0; i < products.size(); i++) {
            items.add(products.get(i));
        }
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTopBannerContent(BannerBean bannerBean) {

    }

    @Override
    public void showFeedBannerContent(BannerBean bannerBean) {

    }
}