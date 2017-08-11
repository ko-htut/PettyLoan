package com.yixun.pettyloan.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.yixun.pettyloan.MainActivity;
import com.yixun.pettyloan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class GuidePictureActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {
    private static final String ASSET_SHARE_BG_FILE = "share_bg.png";
    private List<GuidePictureFragment> mFragmentList;
    private ViewPager mViewPager;
    private LinearLayout mIndicator;
    private GuidePictureAdapter mAdapter;
    private TextView mBackTv;
    private ImageView mChoiceView;

    private int mScreenHeight;
    private int mScreenWidth;
    @Override
    protected void onCreate(Bundle args) {
        super.onCreate(args);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        setContentView(R.layout.act_guide_picture);
//        PreferenceUtil.put(AppConfig.PREFERENCE_SHOW_SPLASH,true);
        mViewPager = (ViewPager) findViewById(R.id.vp_weather_splash);
        mIndicator = (LinearLayout) findViewById(R.id.ll_weather_indicator);
        mBackTv = (TextView) findViewById(R.id.tv_weather_guide_back);
        mBackTv.setOnClickListener(this);
        initFragmentList();
        mAdapter = new GuidePictureAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;

        LayoutParams params = (LayoutParams) mIndicator.getLayoutParams();
        params.bottomMargin = mScreenHeight / 10;
        mChoiceView = (ImageView) mIndicator.getChildAt(0);
        mChoiceView.setAlpha(1f);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFragmentList() {
        mFragmentList = new ArrayList<GuidePictureFragment>();
        for (int i = 0; i < 4; i++) {
            GuidePictureFragment frag = GuidePictureFragment.newInstance(i);
            mFragmentList.add(frag);
        }
    }

    private class GuidePictureAdapter extends FragmentPagerAdapter {

        public GuidePictureAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            return mFragmentList.get(index);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

    }

    @Override
    public void onPageScrollStateChanged(int index) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(final int index) {
        if (mChoiceView != null) {
            mChoiceView.setAlpha(0.3f);
        }
        mChoiceView = (ImageView) mIndicator.getChildAt(index);
        mChoiceView.setAlpha(1f);
    }

    private PointF getScaleAnimatorPivot(int index) {
        switch (index) {
        case 0:
            return new PointF(mScreenWidth / 6, mScreenHeight * 5 / 6);

        case 1:
            return new PointF(mScreenWidth / 6, mScreenHeight / 6);

        case 2:
            return new PointF(mScreenWidth * 5 / 6, mScreenHeight / 6);

        case 3:
            return new PointF(mScreenWidth / 2, mScreenHeight / 2);

        default:
            return new PointF(mScreenWidth / 2, mScreenHeight / 2);
        }
    }

    private PointF getScaleAnimatorDegree(int index) {
        switch (index) {
        case 0:
            return new PointF(1.2f, 1f);

        case 1:
            return new PointF(1f, 1.2f);

        case 2:
            return new PointF(1.2f, 1f);

        case 3:
            return new PointF(1f, 1.2f);

        default:
            return new PointF(1.2f, 1f);
        }
    }

    private AnimatorSet initScaleAnimator(View bg, View title, View content, float pivotX, float pivotY, float start, float end) {
        PropertyValuesHolder changeScaleX = PropertyValuesHolder.ofFloat("scaleX", start, end);
        PropertyValuesHolder changeScaleY = PropertyValuesHolder.ofFloat("scaleY", start, end);
        ObjectAnimator scaleX = ObjectAnimator.ofPropertyValuesHolder(bg, changeScaleX);
        ObjectAnimator scaleY = ObjectAnimator.ofPropertyValuesHolder(bg, changeScaleY);
        bg.setPivotX(pivotX);
        bg.setPivotY(pivotY);

        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        ObjectAnimator titleAlpha = ObjectAnimator.ofPropertyValuesHolder(title, alpha);
        ObjectAnimator contentAlpha = ObjectAnimator.ofPropertyValuesHolder(content, alpha);
        AnimatorSet scaleAnimat = new AnimatorSet();
        scaleAnimat.setDuration(3000);
        scaleAnimat.play(scaleX).with(scaleY).with(titleAlpha).with(contentAlpha);
        return scaleAnimat;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}