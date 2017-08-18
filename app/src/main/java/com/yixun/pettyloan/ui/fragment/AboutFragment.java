package com.yixun.pettyloan.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.HomePagerAdapter;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutFragment extends BaseSupportFragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabs;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[4];

    public static AboutFragment getInstance() {
        AboutFragment sf = new AboutFragment();
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_about;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(getActivity(), R.color.colorPrimary);
        initToolbar();
        bindTabVp();
    }

    @Override
    protected void initData() {
    }

    private void initToolbar() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
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
        HomePagerAdapter adapter = new HomePagerAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
    }

    @OnClick(R.id.back)
    public void clickBack(){
        pop();
    }

}