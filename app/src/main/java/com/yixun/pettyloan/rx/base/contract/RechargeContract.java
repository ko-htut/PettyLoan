package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.RechargeBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface RechargeContract {

    interface View extends BaseView {

        void rechangeOrWithdrawFinish(RechargeBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void rechangeOrWithdraw(Map<String, Object> map);
    }
}
