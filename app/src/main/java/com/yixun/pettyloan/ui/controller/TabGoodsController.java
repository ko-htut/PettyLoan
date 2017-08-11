package com.yixun.pettyloan.ui.controller;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaolei on 17-8-9.
 */

public class TabGoodsController {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public TabGoodsController(Context context) {
        mContext = context;
    }

    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_goods_tabs, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_goods_title);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_goods_content);
        return view;
    }

    public void initTabs(FragmentManager fm, List<String> typeList) {
        if (fm == null) {
            return;
        }

        if (typeList == null || typeList.size() == 0) {
            return;
        }

        List<GoodsFragment> mFragList = new ArrayList<>();

        for (String type : typeList) {
            GoodsFragment fragment = GoodsFragment.newInstance(type);
            mFragList.add(fragment);
        }

        mViewPager.setAdapter(new GoodsAdapter(fm, mFragList));

        if (typeList.size() > 1) {
            for (String type : typeList) {
                mTabLayout.addTab(mTabLayout.newTab().setText(type));
            }

            mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mTabLayout.setScrollPosition(position, 0, false);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            mTabLayout.setVisibility(View.GONE);
        }
    }

    private class GoodsAdapter extends FragmentPagerAdapter {
        List<GoodsFragment> mList;

        private GoodsAdapter(FragmentManager fm, List<GoodsFragment> fragments) {
            super(fm);
            mList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
