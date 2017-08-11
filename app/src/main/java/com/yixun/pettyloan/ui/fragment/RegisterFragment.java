package com.yixun.pettyloan.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kelly.captcha.SwipeCaptchaView;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 17-8-9.
 */
public class RegisterFragment extends BaseSupportFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_input_phone)
    EditText mEtAccount;
    @BindView(R.id.text_input_layout_password)
    TextInputLayout mTextInputLayoutPassword;
    @BindView(R.id.text_input_password)
    TextInputEditText mInputEditTextPassword;
    @BindView(R.id.tv_sign_tip)
    TextView mTip;
    @BindView(R.id.button)
    Button mFinishBtn;

    private LoginFragment.OnLoginSuccessListener mOnLoginSuccessListener;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.OnLoginSuccessListener) {
            mOnLoginSuccessListener = (LoginFragment.OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginSuccessListener");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_register;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.text_blue));
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.text_sign_tip));
        spannableString.setSpan(foregroundColorSpan, 15, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mTip.setText(spannableString);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
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

        showSoftInput(mEtAccount);
    }

    private void showCaptchaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dlg_slide_captcha, null);
        final SwipeCaptchaView mSwipeCaptchaView = (SwipeCaptchaView) view.findViewById(R.id.swipeCaptchaView);
        final SeekBar mSeekBar = (SeekBar) view.findViewById(R.id.dragBar);
        builder.setView(view);
        final AlertDialog dialog = builder.show();

        view.findViewById(R.id.btnChange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeCaptchaView.createCaptcha();
                mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);
            }
        });

        mSwipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
            @Override
            public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                Toast.makeText(getActivity(), "验证成功", Toast.LENGTH_SHORT).show();
                //swipeCaptcha.createCaptcha();
                mSeekBar.setEnabled(false);

                start(RegisterSuccessFragment.newInstance());
                dialog.dismiss();
            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                Log.d("zxt", "matchFailed() called with: swipeCaptchaView = [" + swipeCaptchaView + "]");
                Toast.makeText(getActivity(), "验证失败", Toast.LENGTH_SHORT).show();
                swipeCaptchaView.resetCaptcha();
                mSeekBar.setProgress(0);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSwipeCaptchaView.setCurrentSwipeValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                mSwipeCaptchaView.matchCaptcha();
            }
        });

        //测试从网络加载图片是否ok
        Glide.with(this)
                .load(R.drawable.bg_slide_captcha)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mSwipeCaptchaView.setImageBitmap(resource);
                        mSwipeCaptchaView.createCaptcha();
                    }
                });
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }

    @OnClick(R.id.button)
    public void register(){
        String account = mEtAccount.getText().toString();
        String password = mInputEditTextPassword.getText().toString();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mTextInputLayoutPassword.setError("密码错误不能少于6个字符");
            return;
        }
        //滑动解锁验证
        showCaptchaDialog();
//                // 注册成功
        mOnLoginSuccessListener.onLoginSuccess(account);
//                start(RegisterSuccessFragment.newInstance());
    }
}
