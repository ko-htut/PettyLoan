package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.PayBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface PayContract {

    interface View extends BaseView {

        void buyFinish(PayBean payBean);
        void payFinish(PayBean payBean);
    }

    interface Presenter extends BasePresenter<View> {

        void buy(Map<String, Object> map);

        void pay(String url, Map<String, Object> map);

    }
}
