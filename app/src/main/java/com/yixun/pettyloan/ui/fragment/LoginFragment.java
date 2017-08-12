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

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 17-8-9.
 */
public class LoginFragment extends BaseSupportFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_input_phone)
    EditText mAccount;
    @BindView(R.id.text_input_layout_password)
    TextInputLayout mTextInputLayoutPassword;
    @BindView(R.id.text_input_password)
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
                getActivity().finish();
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

    public interface OnLoginSuccessListener {
        void onLoginSuccess(String account);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @OnClick(R.id.button)
    public void login() {
        String account = mAccount.getText().toString();
        String password = mInputEditTextPassword.getText().toString();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mTextInputLayoutPassword.setError("密码错误不能少于6个字符");
            return;
        }
        // 登录成功
        mOnLoginSuccessListener.onLoginSuccess(account);
        pop();
        getActivity().finish();
    }
}
