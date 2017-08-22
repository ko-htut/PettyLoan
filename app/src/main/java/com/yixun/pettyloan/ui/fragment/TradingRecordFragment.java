package com.yixun.pettyloan.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.HomePagerAdapter;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class TradingRecordFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.tab_layout)
    TabLayout mTabs;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.viewpager_record)
    ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[4];

    public static TradingRecordFragment getInstance(String title) {
        TradingRecordFragment sf = new TradingRecordFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_trading_record;
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
        mTvTitle.setText(mTitle);
    }

    private void bindTabVp() {
        setupViewPager();
        for (int i = 0; i < mFragments.size(); i++) {
            mTabs.addTab(mTabs.newTab().setText(mViewPager.getAdapter().getPageTitle(i)));
        }
        mTabs.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        mTitles = getResources().getStringArray(R.array.tab_trading_record_titles);
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(TradingRecordChildFragment.getInstance(mTitles[i]));
        }
        HomePagerAdapter adapter = new HomePagerAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mTitles.length);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                pop();
                break;
            default:
                break;
        }
    }

}