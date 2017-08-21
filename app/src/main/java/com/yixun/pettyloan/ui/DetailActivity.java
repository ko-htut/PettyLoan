package com.yixun.pettyloan.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.HomePagerAdapter;
import com.yixun.pettyloan.ui.base.BaseSupportActivity;
import com.yixun.pettyloan.ui.fragment.MineFragment;
import com.yixun.pettyloan.ui.fragment.RegisterSuccessFragment;
import com.yixun.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zongkaili on 2017/8/13.
 */

public class DetailActivity extends BaseSupportActivity {
    @BindView(R.id.rootView)
    View rootView;
    @BindView(R.id.ivImage)
    ImageView mImg;
    @BindView(R.id.tab_layout)
    TabLayout mTabs;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[4];

    @Override
    public int getLayoutResource() {
        return R.layout.activity_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        String img = getIntent().getStringExtra("img");
        Glide.with(this)
                .load(R.drawable.pic_banner1)
                .centerCrop()
                .into(mImg);
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, R.color.text_blue);
        initToolBar();
        bindTabVp();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
        mTitles = getResources().getStringArray(R.array.tab_home_titles);
        mFragments.add(RegisterSuccessFragment.newInstance());
        mFragments.add(MineFragment.getInstance("ViewPager " + mTitles[1]));
        mFragments.add(MineFragment.getInstance("ViewPager " + mTitles[2]));
        mFragments.add(MineFragment.getInstance("ViewPager " + mTitles[3]));
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
