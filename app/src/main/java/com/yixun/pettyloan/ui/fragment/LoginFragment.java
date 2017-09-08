package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.presenter.LoginPresenter;
import com.yixun.pettyloan.rx.base.contract.LoginContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.PreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 17-8-9.
 */
public class LoginFragment extends MvpBaseFragment<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_input_phone)
    TextInputEditText mAccount;
    @BindView(R.id.text_input_layout_authcode)
    TextInputLayout mTextInputLayoutPassword;
    @BindView(R.id.text_input_authcode)
    TextInputEditText mInputEditTextPassword;
    @BindView(R.id.button)
    Button mLoginBtn;

    private OnLoginSuccessListener mOnLoginSuccessListener;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginSuccessListener) {
            mOnLoginSuccessListener = (OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginSuccessListener");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar.inflateMenu(R.menu.tool_bar_menu_login);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sign:
                        start(RegisterFragment.newInstance());
                        break;
                }

                return false;
            }
        });

        mInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextInputLayoutPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }

    @Override
    public void loginFinish(CustomerBean customerBean) {
        if (!customerBean.isSuccess()) {
            showErrorMsg("登录失败！");
            return;
        }
        CustomerBean.Data data = customerBean.getData();
        if(data == null)
            return;
        PreferenceUtil.put(AppConfig.PREFERENCE_IS_LOGGED, true);
        PreferenceUtil.put(AppConfig.PREFERENCE_CURRENT_USERNAME, TextUtils.isEmpty(data.getUsername()) ? mAccount.getText().toString().trim() : data.getUsername());
        PreferenceUtil.put(AppConfig.PREFERENCE_CURRENT_SEX, data.getSex());
        PreferenceUtil.put(AppConfig.PREFERENCE_CUSTOMER_ID, data.getId());
        PreferenceUtil.put(AppConfig.PREFERENCE_CUSTOMER_MEMBER, data.getCustomer_number());
        PreferenceUtil.put(AppConfig.PREFERENCE_CUSTOMER_PHONE, data.getPhone());
        PreferenceUtil.put(AppConfig.PREFERENCE_IS_REAL_NAME_AUTH, data.isIs_real_name_auth());
        PreferenceUtil.put(AppConfig.PREFERENCE_TOTAL_ASSETS, (float) data.getTotal_assets());
        PreferenceUtil.put(AppConfig.PREFERENCE_AVAILABLE_ASSETS, (float)data.getAvailable_assets());
        pop();
    }

    @Override
    public void exitFinish(BaseJson<String> bean) {

    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess(String account);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @OnClick(R.id.button)
    public void onClick() {
        String account = mAccount.getText().toString().trim();
        String password = mInputEditTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            mAccount.setError("帐号不能为空");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 3) {
            mTextInputLayoutPassword.setError("密码错误不能少于3个字符");
            return;
        }
        login(account, password);
    }

    private void login(String account, String pwd) {
        Map<String, Object> map = new HashMap<>();
        map.put(CustomerBean.PHONE, account);
        map.put(CustomerBean.PASSWORD, pwd);
        mPresenter.login(map);
    }
}
