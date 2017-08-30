package com.yixun.pettyloan.rx.di.component;

import android.app.Activity;

import com.yixun.pettyloan.rx.di.module.FragmentModule;
import com.yixun.pettyloan.rx.di.scope.FragmentScope;
import com.yixun.pettyloan.ui.fragment.MediaReportFragment;

import dagger.Component;

/**
 * Created by zongkaili on 2017/8/28.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MediaReportFragment mediaReportFragment);

}
