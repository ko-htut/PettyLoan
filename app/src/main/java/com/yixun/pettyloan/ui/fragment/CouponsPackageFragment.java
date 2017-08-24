package com.yixun.pettyloan.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.MyFragmentPagerAdapter;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;

public class CouponsPackageFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.tab_layout)
    TabLayout mTabs;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager_record)
    ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[4];

    public static CouponsPackageFragment getInstance(String title) {
        CouponsPackageFragment sf = new CouponsPackageFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_coupons_package;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        bindTabVp();
    }

    @Override
    protected void initData() {
    }

    private void initToolbar() {
        mToolbar.setTitle(mTitle);
        mToolbar.inflateMenu(R.menu.tool_bar_menu_coupons_package);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sign:
                        Toast.makeText(getContext(), "功能开发中...", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
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
        mTitles = getResources().getStringArray(R.array.tab_coupons_package_titles);
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(SimpleCardFragment.getInstance(mTitles[i]));
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mTitles.length);
        adapter.notifyDataSetChanged();
    }

//    @OnClick(R.id.iv_back)
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.iv_back:
//                pop();
//                break;
//            default:
//                break;
//        }
//    }

}