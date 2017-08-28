package com.yixun.pettyloan.rx.di.component;

import android.app.Activity;

import com.yixun.pettyloan.rx.di.scope.ActivityScope;
import com.yixun.pettyloan.ui.GuidePictureActivity;
import com.yixun.pettyloan.ui.LoginActivity;
import com.yixun.pettyloan.ui.MainActivity;
import com.yixun.pettyloan.ui.SplashActivity;

import dagger.Component;

/**
 * Created by zongkaili on 2017/8/28.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(SplashActivity welcomeActivity);

    void inject(MainActivity mainActivity);

    void inject(GuidePictureActivity zhihuDetailActivity);

    void inject(LoginActivity themeActivity);
}
