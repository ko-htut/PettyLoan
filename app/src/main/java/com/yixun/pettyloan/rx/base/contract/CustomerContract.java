package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface CustomerContract {

    interface View extends BaseView {

        void showContent(CustomerBean customerBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getCustomerInfo();

    }
}
