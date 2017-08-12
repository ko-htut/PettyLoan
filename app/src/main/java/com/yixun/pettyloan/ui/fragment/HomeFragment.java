package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.yixun.pettyloan.entity.Feature;
import com.yixun.pettyloan.entity.FeatureItemViewBinder;
import com.yixun.pettyloan.entity.ProductItemViewBinder;
import com.yixun.pettyloan.mode.GoodsMode;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_banner_top)
    Banner mTopBanner;
    @BindView(R.id.rv_home_feed)
    RecyclerView mFeedRecycler;

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static HomeFragment getInstance(String title) {
        HomeFragment sf = new HomeFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.text_blue);
        bindBanner();
    }

    @Override
    protected void initData() {
        bindFeeds();
    }

    private void bindFeeds() {
        mFeedAdapter = new MultiTypeAdapter();
        mFeedAdapter.register(Category.class, new CategoryItemViewBinder());
        mFeedAdapter.register(GoodsMode.class, new ProductItemViewBinder());
        mFeedAdapter.register(Feature.class, new FeatureItemViewBinder());
        mFeedAdapter.register(Advertisement.class, new AdvertisementItemViewBinder());
        mFeedAdapter.register(BannerFeed.class, new BannerItemViewBinder());
        mFeedRecycler.setAdapter(mFeedAdapter);
        items = new ArrayList<>();
        items.add(new Category("投资理财"));
        items.add(new GoodsMode("产品１", "10", "% + 0.32%"));
        items.add(new GoodsMode("产品2", "20", "% + 0.32%"));
        items.add(new GoodsMode("产品3", "20", "% + 0.32%"));
        items.add(new Category("实物理财"));
        items.add(new GoodsMode("产品１", "30", "% + 0.32%"));
        items.add(new GoodsMode("产品2", "40", "% + 0.32%"));
        items.add(new GoodsMode("产品3", "50", "% + 0.32%"));
        items.add(new Feature("黄金商城", "", R.drawable.icon_gold,
                "珠宝商城", "", R.drawable.icon_jewelry));
        items.add(new Advertisement("小额贷并购幻视信贷工厂"));
        List<Object> urlList = new ArrayList<>();
        urlList.add(R.drawable.pic_banner1);
        urlList.add(R.drawable.pic_banner2);
        items.add(new BannerFeed(urlList));
        items.add(new Feature("关于小额贷", "了解历史及最新动态", R.drawable.icon_loan,
                "多重保障", "全方位安全保障", R.drawable.icon_safeguard));
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

    private void bindBanner() {
        List<Object> urlList = new ArrayList<>();
        urlList.add(R.drawable.pic_banner1);
        urlList.add(R.drawable.pic_banner2);
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
                return new ImageView(context);
            }
        });
        mTopBanner.setImages(urlList).start();
    }
}