package com.yixun.pettyloan.ui.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.MyFragmentPagerAdapter;
import com.yixun.pettyloan.adapter.multitype.MultiTypeAdapter;
import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.presenter.ProductsPresenter;
import com.yixun.pettyloan.rx.base.contract.ProductsContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EntityFragment extends MvpBaseFragment<ProductsPresenter> implements ProductsContract.View {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner_entity)
    Banner mTopBanner;
    @BindView(R.id.tab_entity_title)
    TabLayout mTabs;
    @BindView(R.id.vp_entity_content)
    ViewPager mViewPager;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[2];

    MultiTypeAdapter mFeedAdapter;
    List<Object> items;

    public static EntityFragment getInstance(String title) {
        EntityFragment sf = new EntityFragment();
        return sf;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToorBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    private void initToorBar() {
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_entity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.bg_gray_medium);
        initBanner();
        configRefresh();
        bindTabVp();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.getBannerData(3);
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

    private void bindTabVp() {
        setupViewPager();
        for (int i = 0; i < mFragments.size(); i++) {
            mTabs.addTab(mTabs.newTab().setText(mViewPager.getAdapter().getPageTitle(i)));
        }
        mTabs.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        mTitles = getResources().getStringArray(R.array.tab_entity_titles);
        for (int i = 0; i < mTitles.length; i++) {
            if (i == 0) {
                mFragments.add(EntityChildOneFragment.getInstance(mTitles[i]));
            } else if (i == 1) {
                mFragments.add(EntityChildTwoFragment.getInstance(mTitles[i]));
            }
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mTitles.length);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showContent(ProductsListBean productsListBean) {

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
        mRefresh.setRefreshing(false);
    }

}