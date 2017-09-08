package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import com.yixun.pettyloan.entity.Advertisement;
import com.yixun.pettyloan.entity.AdvertisementItemViewBinder;
import com.yixun.pettyloan.entity.BannerFeed;
import com.yixun.pettyloan.entity.BannerItemViewBinder;
import com.yixun.pettyloan.entity.Category;
import com.yixun.pettyloan.entity.CategoryItemViewBinder;
import com.yixun.pettyloan.entity.FeatureOne;
import com.yixun.pettyloan.entity.FeatureOneItemViewBinder;
import com.yixun.pettyloan.entity.FeatureTwo;
import com.yixun.pettyloan.entity.FeatureTwoItemViewBinder;
import com.yixun.pettyloan.entity.ProductItemViewBinder;
import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.presenter.ProductsPresenter;
import com.yixun.pettyloan.rx.base.contract.ProductsContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class HomeFragment extends MvpBaseFragment<ProductsPresenter> implements ProductsContract.View {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_banner_top)
    Banner mTopBanner;
    @BindView(R.id.rv_home_feed)
    RecyclerView mFeedRecycler;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;

    MultiTypeAdapter mFeedAdapter;
    List<ProductDetailBean.Data> products;
    List<Object> items;

    public static HomeFragment getInstance(String title) {
        HomeFragment sf = new HomeFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.blue_medium);
        initBanner();
        configRefresh();
    }

    @Override
    protected void initData() {
        bindFeeds();
        Map<String, Integer> map = new HashMap<>();
//        map.put(ProductsListBean.PAGE_SIZE,0);
        map.put(ProductsListBean.QUERY_TYPE, 1);
        mPresenter.getProductsData(map);
        mPresenter.getBannerData(0);
    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Category.class, new CategoryItemViewBinder());
        mFeedAdapter.register(ProductDetailBean.Data.class, new ProductItemViewBinder(this));
        mFeedAdapter.register(FeatureOne.class, new FeatureOneItemViewBinder());
        mFeedAdapter.register(FeatureTwo.class, new FeatureTwoItemViewBinder(context));
        mFeedAdapter.register(Advertisement.class, new AdvertisementItemViewBinder());
        mFeedAdapter.register(BannerFeed.class, new BannerItemViewBinder());
        mFeedRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        mFeedAdapter.setItems(items);
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToorBar();
    }

    private void initToorBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void initBanner() {
        mTopBanner.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View imageView) {
                Glide.with(context.getApplicationContext())
                        .load(path)
                        .crossFade()
                        .centerCrop()
                        .into((ImageView) imageView);
            }

            @Override
            public View createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setTransitionName(getString(R.string.transition_banner));
                }
                return imageView;
            }
        });
        mTopBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//                intent.putExtra("img", String.valueOf(feedBannerList.get(position)));
//                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                        mTopBanner, getString(R.string.transition_banner));
//                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });
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
        items.clear();
        Map<String, Integer> map = new HashMap<>();
//        map.put(ProductsListBean.PAGE_SIZE,0);
        map.put(ProductsListBean.QUERY_TYPE, 1);
        mPresenter.getProductsData(map);
        mPresenter.getBannerData(0);
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
        products = productsListBean.getData().getProductsList();
        List<ProductDetailBean.Data> investProducts = new ArrayList<>();
        List<ProductDetailBean.Data> entityProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getPro_type() == 1) {
                investProducts.add(products.get(i));
            } else if (products.get(i).getPro_type() == 0) {
                entityProducts.add(products.get(i));
            }
        }

        items.add(new Category("投资理财"));
        for (int i = 0; i < investProducts.size(); i++) {
            items.add(investProducts.get(i));
        }
        items.add(new Category("实物理财"));
        for (int i = 0; i < investProducts.size(); i++) {
            items.add(entityProducts.get(i));
        }
        items.add(new FeatureOne("黄金商城", R.drawable.icon_gold,
                "珠宝商城", R.drawable.icon_jewelry));
        items.add(new Advertisement("小额贷并购幻视信贷工厂"));
        items.add(new FeatureTwo("关于小额贷", "了解历史及最新动态", R.drawable.icon_loan,
                "多重保障", "全方位安|全保障", R.drawable.icon_safeguard));

        mFeedAdapter.notifyDataSetChanged();
        mPresenter.getBannerData(1);
    }

    @Override
    public void showTopBannerContent(BannerBean bannerBean) {
        List<BannerBean.Data.Banner> banner = bannerBean.getData().getBannerList();
        List<Object> topBannerList = new ArrayList<>();
        for (int i = 0; i < banner.size(); i++) {
            topBannerList.add(Apis.HOST + banner.get(i).getBannerUrl());
        }
        mTopBanner.setImages(topBannerList).start();
    }

    @Override
    public void showFeedBannerContent(BannerBean bannerBean) {
        List<BannerBean.Data.Banner> banner = bannerBean.getData().getBannerList();
        List<Object> feedBannerList = new ArrayList<>();
        for (int i = 0; i < banner.size(); i++) {
            feedBannerList.add(Apis.HOST + banner.get(i).getBannerUrl());
        }
        if (products == null)
            return;
        items.add(products.size() + 3, new BannerFeed(feedBannerList));
        mFeedAdapter.notifyDataSetChanged();
    }
}