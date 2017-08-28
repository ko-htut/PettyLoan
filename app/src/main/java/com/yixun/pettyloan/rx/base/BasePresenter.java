package com.yixun.pettyloan.rx.base;

/**
 * Created by zongkaili on 2017/8/28.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
