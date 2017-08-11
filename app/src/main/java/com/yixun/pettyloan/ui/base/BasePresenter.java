package com.yixun.pettyloan.ui.base;

import android.content.Context;

import com.yixun.pettyloan.rx.RxManager;

/**
 * 基类presenter
 * Created by zongkaili on 17-8-9.
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
