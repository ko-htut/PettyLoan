package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MySettingFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static MySettingFragment getInstance(String title) {
        MySettingFragment sf = new MySettingFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my_setting;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
    }

    @Override
    protected void initData() {
    }

    private void initToolbar() {
        mTvTitle.setText(mTitle);
    }

    @OnClick({R.id.iv_back,R.id.iv_avatar,R.id.tv_about})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                pop();
                break;
            case R.id.iv_avatar:
                Toast.makeText(context,"功能开发中...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_about:
                Toast.makeText(context,"功能开发中...",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}