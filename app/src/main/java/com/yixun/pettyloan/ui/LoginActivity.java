package com.yixun.pettyloan.ui;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportActivity;
import com.yixun.pettyloan.ui.fragment.LoginFragment;

/**
 * Created by zongkaili on 17-8-9.
 */
public class LoginActivity extends BaseSupportActivity implements LoginFragment.OnLoginSuccessListener {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        loadRootFragment(R.id.fl_container, LoginFragment.newInstance());
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
            finish();
        }
    }
}
