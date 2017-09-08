package com.yixun.pettyloan.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.presenter.LoginPresenter;
import com.yixun.pettyloan.rx.base.contract.LoginContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.LogUtils;
import com.yixun.pettyloan.utils.PreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class MySettingFragment extends MvpBaseFragment<LoginPresenter> implements LoginContract.View {
    private static final String TAG = MySettingFragment.class.getSimpleName();
    private static final int REQUEST_CODE = 0;
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_user_id)
    TextView mTvUserId;
    @BindView(R.id.tv_real_name)
    TextView mTvRealName;
    @BindView(R.id.tv_user_phone)
    TextView mTvUserPhone;

    public static MySettingFragment getInstance(String title) {
        MySettingFragment sf = new MySettingFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
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
        mTvUserId.setText(String.valueOf(PreferenceUtil.getInt(AppConfig.PREFERENCE_CUSTOMER_ID, 0)));
        mTvRealName.setText(!PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_REAL_NAME_AUTH, false) ?
                getResources().getString(R.string.setting_authentication_no) : PreferenceUtil.getString(AppConfig.PREFERENCE_CURRENT_USERNAME, ""));
        mTvUserPhone.setText(PreferenceUtil.getString(AppConfig.PREFERENCE_CUSTOMER_PHONE, ""));
    }

    private void initToolbar() {
        mTvTitle.setText(mTitle);
    }

    @OnClick({R.id.iv_back, R.id.ll_avatar, R.id.ll_realname_authentication,
            R.id.ll_bank_card, R.id.ll_phone_number, R.id.tv_change_login_pwd,
            R.id.tv_set_trading_pwd, R.id.tv_text_size, R.id.ll_risk_assessment,
            R.id.tv_about, R.id.rl_exit_safely})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            case R.id.ll_avatar:
                Toast.makeText(context, "功能开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_realname_authentication:
                if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_REAL_NAME_AUTH, false)) {
                    Toast.makeText(context, "已经实名认证过了", Toast.LENGTH_SHORT).show();
                    break;
                }
                startForResult(RealnameAuthFragment.newInstance(), REQUEST_CODE);
                break;
            case R.id.tv_about:
                EventBus.getDefault().post(new StartBrotherEvent(AboutPettyloanFragment.getInstance("关于小额贷")));
                break;
            case R.id.rl_exit_safely:
                showSureExitDialog();
                break;
            default:
                Toast.makeText(context, "功能开发中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        LogUtils.logd(TAG, " requestCode : " + requestCode + " resultCode : " + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            mTvRealName.setText(data.getString(CustomerBean.USERNAME));
        }
    }

    private void showSureExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("确定退出吗？");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.exit();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void loginFinish(CustomerBean customerBean) {

    }

    @Override
    public void exitFinish(BaseJson<String> bean) {
        if (!bean.isSuccess()) {
            showErrorMsg(bean.getMsg());
            return;
        }
        PreferenceUtil.put(AppConfig.PREFERENCE_IS_LOGGED, false);
        PreferenceUtil.put(AppConfig.PREFERENCE_CURRENT_USERNAME, getString(R.string.not_login));
        PreferenceUtil.put(AppConfig.PREFERENCE_TOTAL_ASSETS, 0f);
        PreferenceUtil.put(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0f);
        Toast.makeText(context, bean.getData(), Toast.LENGTH_SHORT).show();
        pop();
    }
}