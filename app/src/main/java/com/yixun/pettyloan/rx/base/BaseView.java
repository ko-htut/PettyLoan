package com.yixun.pettyloan.rx.base;

/**
 * Created by zongkaili on 2017/8/28.
 */

public interface BaseView {
    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError(Throwable e);

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
