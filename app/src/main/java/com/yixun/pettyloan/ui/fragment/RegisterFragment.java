package com.yixun.pettyloan.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
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
import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.RegisterBean;
import com.yixun.pettyloan.presenter.RegisterPresenter;
import com.yixun.pettyloan.rx.base.contract.RegisterContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 17-8-9.
 */
public class RegisterFragment extends MvpBaseFragment<RegisterPresenter> implements RegisterContract.View {
    private static final int UPDATE_COUNTDOWN = 0;
    private static final int TIME_COUNTDOWN = 30;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_input_layout_phone)
    TextInputLayout mTILPhone;
    @BindView(R.id.text_input_phone)
    EditText mEtAccount;
    @BindView(R.id.text_input_layout_authcode)
    TextInputLayout mTILAuthcode;
    @BindView(R.id.text_input_authcode)
    TextInputEditText mETAuthcode;
    @BindView(R.id.text_input_layout_pwd)
    TextInputLayout mTILPwd;
    @BindView(R.id.text_input_pwd)
    TextInputEditText mETPwd;
    @BindView(R.id.text_input_layout_invite_code)
    TextInputLayout mTILInviteCode;
    @BindView(R.id.text_input_invite_code)
    EditText mETInviteCode;
    @BindView(R.id.btn_get_auth_code)
    Button mBtnGetAuthCode;
    @BindView(R.id.tv_sign_tip)
    TextView mTip;
    @BindView(R.id.button)
    Button mFinishBtn;
    private int time = TIME_COUNTDOWN;
    private LoginFragment.OnLoginSuccessListener mOnLoginSuccessListener;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_COUNTDOWN:
                    time--;
                    mBtnGetAuthCode.setText(time + " 秒");
                    if (time > 0) {
                        mHandler.sendEmptyMessageDelayed(UPDATE_COUNTDOWN, 1000);
                        break;
                    }
                    mHandler.removeMessages(UPDATE_COUNTDOWN);
                    mBtnGetAuthCode.setText(getResources().getString(R.string.text_sign_get_auth_code));
                    mBtnGetAuthCode.setClickable(true);
                    time = TIME_COUNTDOWN;
                    break;
                default:
                    break;
            }
        }
    };

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
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
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.text_blue));
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.text_sign_tip));
        spannableString.setSpan(foregroundColorSpan, 15, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mTip.setText(spannableString);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        mETAuthcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTILAuthcode.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showSoftInput(mEtAccount);
    }

    @Override
    protected void initData() {

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

                register();

                dialog.dismiss();

            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
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

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeMessages(UPDATE_COUNTDOWN);
        mBtnGetAuthCode.setText(getResources().getString(R.string.text_sign_get_auth_code));
        mBtnGetAuthCode.setClickable(true);
        time = TIME_COUNTDOWN;
    }

    @Override
    public boolean onBackPressedSupport() {
        mHandler.removeMessages(UPDATE_COUNTDOWN);
        mBtnGetAuthCode.setText(getResources().getString(R.string.text_sign_get_auth_code));
        mBtnGetAuthCode.setClickable(true);
        time = TIME_COUNTDOWN;
        return super.onBackPressedSupport();
    }

    @OnClick({R.id.btn_get_auth_code, R.id.btn_get_invite_code, R.id.button})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_auth_code:
                getAuthCode(mEtAccount.getText().toString().trim());
                break;
            case R.id.btn_get_invite_code:
                break;
            case R.id.button:
                String account = mEtAccount.getText().toString().trim();
                String authcode = mETAuthcode.getText().toString().trim();
                String password = mETPwd.getText().toString().trim();
                if (TextUtils.isEmpty(account) || account.length() < 11) {
                    mTILPhone.setError("手机号码不能少于11位");
                    return;
                }
                if (TextUtils.isEmpty(authcode) || authcode.length() < 4) {
                    mTILAuthcode.setError("验证码不能少于4个字符");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mTILPwd.setError("密码不能为空");
                    return;
                }

                //滑动解锁验证
                showCaptchaDialog();
                break;
            default:
                break;
        }
    }

    private void getAuthCode(String account) {
        if (TextUtils.isEmpty(account) || account.length() < 11) {
            mTILPhone.setError("手机号码不能少于11位");
            return;
        }
        mTILPhone.setErrorEnabled(false);
        mPresenter.getAuthCode(account);
        mBtnGetAuthCode.setText(time + " 秒");
        mBtnGetAuthCode.setClickable(false);
        mHandler.sendEmptyMessageDelayed(UPDATE_COUNTDOWN, 1000);
    }

    private void register() {
        Map<String, Object> map = new HashMap<>();
        map.put(RegisterBean.PHONE, mEtAccount.getText().toString().trim());
        map.put(RegisterBean.PASSWORD, mETPwd.getText().toString().trim());
        map.put(RegisterBean.SECURITY_CODE, mETAuthcode.getText().toString().trim());
        map.put(RegisterBean.INVITATION_CODE, mETInviteCode.getText().toString().trim());
        mPresenter.register(map);
    }

    @Override
    public void showContent(BaseJson<String> bean) {
        if(!bean.isSuccess()){
            showErrorMsg(bean.getMsg());
            return;
        }
        Toast.makeText(context,bean.getData(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFinish(RegisterBean registerBean) {
        startWithPop(RegisterSuccessFragment.newInstance());
    }

}
