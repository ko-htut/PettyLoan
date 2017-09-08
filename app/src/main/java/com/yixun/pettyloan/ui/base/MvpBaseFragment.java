package com.yixun.pettyloan.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yixun.pettyloan.App;
import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;
import com.yixun.pettyloan.rx.di.component.DaggerFragmentComponent;
import com.yixun.pettyloan.rx.di.component.FragmentComponent;
import com.yixun.pettyloan.rx.di.module.FragmentModule;
import com.yixun.pettyloan.utils.LogUtils;
import com.yixun.pettyloan.utils.PreferenceUtil;
import com.yixun.pettyloan.utils.SnackbarUtil;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by zongkaili on 2017/8/28.
 */
public abstract class MvpBaseFragment<T extends BasePresenter> extends BaseSupportFragment implements BaseView {
    @Inject
    public T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent
                .builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
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
        SnackbarUtil.showShort(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError(Throwable e) {
        if (e == null)
            return;
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case 401:
                    showErrorMsg("登录失效，请重新登录");
                    PreferenceUtil.put(AppConfig.PREFERENCE_IS_LOGGED, false);
                    PreferenceUtil.put(AppConfig.PREFERENCE_CURRENT_USERNAME, getString(R.string.not_login));
                    PreferenceUtil.put(AppConfig.PREFERENCE_TOTAL_ASSETS, 0f);
                    PreferenceUtil.put(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0f);
                    break;
                case 400:
                case 406:
                case 409:
                    ResponseBody errorBody = ((HttpException) e).response().errorBody();
                    try {
                        String errorJsonStr = errorBody.string();
                        JsonObject jsonObject = new JsonParser().parse(errorJsonStr).getAsJsonObject();
                        if (jsonObject.has("msg")) {
                            JsonElement asString = jsonObject.get("msg");
                            String msg = asString.toString().substring(1, asString.toString().length() - 1);
                            LogUtils.logd(" stateError 400+ : " + msg);
                            showErrorMsg(msg);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    if (!TextUtils.isEmpty(e.getMessage()))
                        showErrorMsg(e.getMessage());
                    else
                        showErrorMsg("数据加载失败ヽ(≧Д≦)ノ");
                    break;
            }
        }
        LogUtils.logd(" stateError getCause : " + e.getCause());
        LogUtils.logd(" stateError getMessage : " + e.getMessage());
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