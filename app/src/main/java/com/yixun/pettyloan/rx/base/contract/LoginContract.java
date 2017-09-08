package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface LoginContract {

    interface View extends BaseView {
        void loginFinish(CustomerBean customerBean);
        void exitFinish(BaseJson<String> bean);
    }

    interface Presenter extends BasePresenter<View> {
        void login(Map<String, Object> map);
        void exit();
    }
}
