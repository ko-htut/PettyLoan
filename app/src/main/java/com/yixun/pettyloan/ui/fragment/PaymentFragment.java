package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static PaymentFragment getInstance(String title) {
        PaymentFragment sf = new PaymentFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pay;
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
        mTvTitle.setText(mTitle);
    }

    private void initToolbar() {
        mTvTitle.setText(mTitle);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            default:
                break;
        }
    }
}