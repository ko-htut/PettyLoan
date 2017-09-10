package com.yixun.pettyloan.ui;

import android.widget.Toast;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.presenter.MainPresenter;
import com.yixun.pettyloan.rx.base.contract.MainContract;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.base.MvpBaseActivity;
import com.yixun.pettyloan.ui.fragment.LoginFragment;
import com.yixun.pettyloan.ui.fragment.MainFragment;

/**
 * Created by zongkaili on 17-8-9.
 */
public class MainActivity extends MvpBaseActivity<MainPresenter> implements MainContract.View,LoginFragment.OnLoginSuccessListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        BaseSupportFragment fragment = (BaseSupportFragment) findFragment(MainFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }

    private void goLogin() {
        start(LoginFragment.newInstance());
    }

    @Override
    public void onLoginSuccess(String account) {

    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showUpdateDialog(String versionContent) {

    }

    @Override
    public void startDownloadService() {

    }
}
