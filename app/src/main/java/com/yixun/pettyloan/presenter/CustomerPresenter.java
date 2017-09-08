package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.CustomerContract;
import com.yixun.pettyloan.rx.base.contract.LoginContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class CustomerPresenter extends RxPresenter<CustomerContract.View> implements CustomerContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public CustomerPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getCustomerInfo() {
        addSubscribe(mDataManager.getCustomerInfo()
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
                        mView.showContent(customerBean);
                    }
                })
        );
    }
}
