package com.yixun.pettyloan.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.HomePagerAdapter;
import com.yixun.pettyloan.entity.TabEntity;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.tablayout.CommonTabLayout;
import com.yixun.tablayout.listener.CustomTabEntity;
import com.yixun.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;

public class MainFragment extends BaseSupportFragment {
    @BindView(R.id.home_vp)
    ViewPager mViewPager;
    @BindView(R.id.home_tablayout)
    CommonTabLayout mTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[4];
    private int[] mIconUnselectIds;
    private int[] mIconSelectIds;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        bindData();
        initFragment();
        initWidge();
        bindWidge();
    }

    private void bindData(){
        mTitles = getResources().getStringArray(R.array.tab_home_titles);
        mIconUnselectIds = new int[]{R.drawable.tab_home_unselect, R.drawable.tab_invest_unselect,
                R.drawable.tab_entity_unselect, R.drawable.tab_mine_unselect};
        mIconSelectIds = new int[]{R.drawable.tab_home_select, R.drawable.tab_invest_select,
                R.drawable.tab_entity_select, R.drawable.tab_mine_select};
    }

    @Override
    protected void initData() {
    }

    private void initFragment() {
        mFragments.add(HomeFragment.getInstance("ViewPager " + mTitles[0]));
        mFragments.add(InvestFragment.getInstance("ViewPager " + mTitles[1]));
        mFragments.add(EntityFragment.getInstance("ViewPager " + mTitles[2]));
        mFragments.add(MineFragment.getInstance("ViewPager " + mTitles[3]));
    }

    public void initWidge() {
        mViewPager.setAdapter(new HomePagerAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles));
        mViewPager.setOffscreenPageLimit(4);
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

    /**
     * start other BrotherFragment
     */
    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }
}