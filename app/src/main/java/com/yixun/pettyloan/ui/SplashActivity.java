package com.yixun.pettyloan.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportActivity;
import com.yixun.pettyloan.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class SplashActivity extends BaseSupportActivity {
    private static final int UPDATE_COUNTDOWN = 0;
    @BindView(R.id.tv_splash_skip)
    TextView mTvKip;

    @BindView(R.id.rl_splash_start)
    RelativeLayout mRlStart;

    private int time = 5;
    private Message message;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_COUNTDOWN:
                    time--;
                    mTvKip.setText(getResources().getString(R.string.splash_skip, String.valueOf(time)));
                    if (time > 0) {
                        mHandler.sendEmptyMessageDelayed(UPDATE_COUNTDOWN, 1000);
                        break;
                    }
                    if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_SHOW_SPLASH, false)) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(SplashActivity.this, GuidePictureActivity.class));
                    }
                    mHandler.removeMessages(UPDATE_COUNTDOWN);
                    finish();
                default:
                    break;
            }
        }
    };

    @Override
    public int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initCountDown();
    }

    private void initCountDown() {
        mTvKip.setText(getResources().getString(R.string.splash_skip, String.valueOf(time)));
        message = new Message();
        message.what = UPDATE_COUNTDOWN;
        message.obj = time;
        mHandler.sendMessageDelayed(message, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeMessages(UPDATE_COUNTDOWN);
    }

    @OnClick(R.id.rl_splash_start)
    public void start() {
        if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_SHOW_SPLASH, false)) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        else {
            startActivity(new Intent(SplashActivity.this, GuidePictureActivity.class));
        }
        mHandler.removeMessages(UPDATE_COUNTDOWN);
        finish();
    }

    @OnClick(R.id.tv_splash_skip)
    public void skip() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        mHandler.removeMessages(UPDATE_COUNTDOWN);
        finish();
    }
}
