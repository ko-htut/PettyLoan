package com.yixun.pettyloan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yixun.pettyloan.BaseActivity;
import com.yixun.pettyloan.MainActivity;
import com.yixun.pettyloan.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class SplashActivity extends BaseActivity {
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
                    if (time == 0) {
//                    if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_SHOW_SPLASH, false))
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                else
                        startActivity(new Intent(SplashActivity.this, GuidePictureActivity.class));
                        finish();
                    }
                    mHandler.sendEmptyMessageDelayed(UPDATE_COUNTDOWN, 1000);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
//        if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_SHOW_SPLASH, false))
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                else
//                    startActivity(new Intent(SplashActivity.this, GuidePictureActivity.class));
        finish();
    }

    @OnClick(R.id.tv_splash_skip)
    public void skip() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
