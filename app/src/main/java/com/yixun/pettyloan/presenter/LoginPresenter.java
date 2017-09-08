package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.LoginContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void login(Map<String, Object> map) {
        addSubscribe(mDataManager.postLogin(map)
                .compose(RxUtil.<CustomerBean>rxSchedulerHelper())
                .map(new Function<CustomerBean, CustomerBean>() {
                    @Override
                    public CustomerBean apply(CustomerBean customerBean) {
                        return customerBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<CustomerBean>(mView) {
                    @Override
                    public void onNext(CustomerBean customerBean) {
                        mView.loginFinish(customerBean);
                    }
                })
        );
    }

    @Override
    public void exit() {
        addSubscribe(mDataManager.deleteExit()
                .compose(RxUtil.<BaseJson<String>>rxSchedulerHelper())
                .map(new Function<BaseJson<String>, BaseJson<String>>() {
                    @Override
                    public BaseJson<String> apply(BaseJson<String> bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<BaseJson<String>>(mView) {
                    @Override
                    public void onNext(BaseJson<String> bean) {
                        mView.exitFinish(bean);
                    }
                })
        );
    }
}
