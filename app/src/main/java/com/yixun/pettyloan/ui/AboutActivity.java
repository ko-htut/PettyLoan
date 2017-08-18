package com.yixun.pettyloan.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.HomePagerAdapter;
import com.yixun.pettyloan.ui.base.BaseSupportActivity;
import com.yixun.pettyloan.ui.fragment.MineFragment;
import com.yixun.pettyloan.ui.fragment.RegisterSuccessFragment;
import com.yixun.pettyloan.ui.fragment.SimpleCardFragment;
import com.yixun.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 17-8-14.
 */

public class AboutActivity extends BaseSupportActivity {
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
        return R.layout.activity_about;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, R.color.colorPrimary);
        initToolBar();
        bindTabVp();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
        mTitles = getResources().getStringArray(R.array.tab_about_titles);
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(SimpleCardFragment.getInstance("ViewPager " + mTitles[i]));
        }
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
    }

    @OnClick(R.id.back)
    public void clickBack(){
        finish();
    }
}
