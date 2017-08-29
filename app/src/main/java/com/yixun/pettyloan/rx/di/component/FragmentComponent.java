package com.yixun.pettyloan.rx.di.component;

import android.app.Activity;

import com.jess.arms.di.component.AppComponent;
import com.yixun.pettyloan.rx.di.module.FragmentModule;
import com.yixun.pettyloan.rx.di.scope.FragmentScope;
import com.yixun.pettyloan.ui.fragment.MediaReportFragment;

import dagger.Component;

/**
 * Created by zongkaili on 2017/8/29.
 */
@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MediaReportFragment mediaReportFragment);
}
