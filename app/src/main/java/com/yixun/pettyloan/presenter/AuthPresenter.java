package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.AuthContract;
import com.yixun.pettyloan.rx.base.contract.LoginContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class AuthPresenter extends RxPresenter<AuthContract.View> implements AuthContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public AuthPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void auth(Map<String, Object> map) {
        addSubscribe(mDataManager.putAuth(map)
                .compose(RxUtil.<CustomerBean>rxSchedulerHelper())
                .map(new Function<CustomerBean, CustomerBean>() {
                    @Override
                    public CustomerBean apply(CustomerBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<CustomerBean>(mView) {
                    @Override
                    public void onNext(CustomerBean bean) {
                        mView.authFinish(bean);
                    }
                })
        );
    }
}
