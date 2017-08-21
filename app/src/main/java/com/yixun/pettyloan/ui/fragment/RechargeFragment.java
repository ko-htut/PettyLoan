package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_add_bank_card_select)
    ImageView mABCSelectIv;
    @BindView(R.id.iv_wechat_pay_select)
    ImageView mWPSelectIv;

    public static RechargeFragment getInstance(String title) {
        RechargeFragment sf = new RechargeFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recharge;
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
        mToolbar.setTitle(mTitle);
        mToolbar.inflateMenu(R.menu.tool_bar_menu_recharge);
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

    @OnClick({R.id.ll_add_bank_card_root,R.id.ll_wechat_pay_root})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_add_bank_card_root:
                mABCSelectIv.setVisibility(View.VISIBLE);
                mWPSelectIv.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_wechat_pay_root:
                mWPSelectIv.setVisibility(View.VISIBLE);
                mABCSelectIv.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }
}