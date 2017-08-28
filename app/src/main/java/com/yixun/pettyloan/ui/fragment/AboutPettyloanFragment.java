package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutPettyloanFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static AboutPettyloanFragment getInstance(String title) {
        AboutPettyloanFragment sf = new AboutPettyloanFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_about_pettyloan;
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

    @OnClick({R.id.iv_back, R.id.tv_share_to_friends, R.id.ll_go_grade})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            case R.id.tv_share_to_friends:
                Toast.makeText(context, "功能开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_go_grade:
                Toast.makeText(context, "功能开发中...", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}