package com.yixun.pettyloan;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yixun.pettyloan.adapter.HomePagerAdapter;
import com.yixun.pettyloan.entity.TabEntity;
import com.yixun.pettyloan.ui.base.BaseSupportActivity;
import com.yixun.pettyloan.ui.fragment.EntityFragment;
import com.yixun.pettyloan.ui.fragment.HomeFragment;
import com.yixun.pettyloan.ui.fragment.InvestFragment;
import com.yixun.pettyloan.ui.fragment.MineFragment;
import com.yixun.pettyloan.utils.ViewFindUtils;
import com.yixun.tablayout.CommonTabLayout;
import com.yixun.tablayout.listener.CustomTabEntity;
import com.yixun.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class MainActivity extends BaseSupportActivity {
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[4];
    private int[] mIconUnselectIds;
    private int[] mIconSelectIds;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initData();
        initFragment();
        initWidge();
        bindWidge();
    }

    private void initData() {
        mTitles = getResources().getStringArray(R.array.tab_home_titles);
        mIconUnselectIds = new int[]{R.drawable.tab_home_unselect, R.drawable.tab_invest_unselect,
                R.drawable.tab_entity_unselect, R.drawable.tab_mine_unselect};
        mIconSelectIds = new int[]{R.drawable.tab_home_select, R.drawable.tab_invest_select,
                R.drawable.tab_entity_select, R.drawable.tab_mine_select};
    }

    public void initWidge() {
        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.home_vp);
        mViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout = ViewFindUtils.find(mDecorView, R.id.home_tablayout);
    }

    private void initFragment() {
        mFragments.add(HomeFragment.getInstance("ViewPager " + mTitles[0]));
        mFragments.add(InvestFragment.getInstance("ViewPager " + mTitles[1]));
        mFragments.add(EntityFragment.getInstance("ViewPager " + mTitles[2]));
        mFragments.add(MineFragment.getInstance("ViewPager " + mTitles[3]));
    }

    private void bindWidge() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
