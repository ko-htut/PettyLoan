package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import org.greenrobot.eventbus.EventBus;

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

    @OnClick({R.id.iv_back,R.id.ll_avatar,R.id.ll_realname_authentication,
            R.id.ll_bank_card,R.id.ll_phone_number,R.id.tv_change_login_pwd,
            R.id.tv_set_trading_pwd,R.id.tv_text_size,R.id.ll_risk_assessment,
            R.id.tv_about,R.id.rl_exit_safely})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                pop();
                break;
            case R.id.ll_avatar:
                Toast.makeText(context,"功能开发中...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_about:
                EventBus.getDefault().post(new StartBrotherEvent(AboutPettyloanFragment.getInstance("关于小额贷")));
                break;
            default:
                Toast.makeText(context,"功能开发中...",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}