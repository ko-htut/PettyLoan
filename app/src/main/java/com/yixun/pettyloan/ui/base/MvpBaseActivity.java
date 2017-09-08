package com.yixun.pettyloan.ui.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.yixun.pettyloan.App;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;
import com.yixun.pettyloan.rx.di.component.ActivityComponent;
import com.yixun.pettyloan.rx.di.component.DaggerActivityComponent;
import com.yixun.pettyloan.rx.di.module.ActivityModule;
import com.yixun.pettyloan.utils.SnackbarUtil;

import javax.inject.Inject;

/**
 * Created by zongkaili on 2017/8/28.
 *  MVP activity基类
 */
public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseSupportActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError(Throwable e) {
    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}