package com.yixun.pettyloan.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.yixun.pettyloan.App;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;
import com.yixun.pettyloan.rx.di.component.DaggerFragmentComponent;
import com.yixun.pettyloan.rx.di.component.FragmentComponent;
import com.yixun.pettyloan.rx.di.module.FragmentModule;
import com.yixun.pettyloan.utils.SnackbarUtil;

import javax.inject.Inject;

/**
 * Created by zongkaili on 2017/8/28.
 */
public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseSupportFragment implements BaseView {
    @Inject
    public T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent
                .builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {
//        if (currentState == STATE_MAIN)
//            return;
//        hideCurrentView();
//        currentState = STATE_MAIN;
//        viewMain.setVisibility(View.VISIBLE);
    }

    protected abstract void initInject();
}