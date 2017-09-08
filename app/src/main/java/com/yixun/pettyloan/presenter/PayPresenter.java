package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.PayBean;
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.PayContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class PayPresenter extends RxPresenter<PayContract.View> implements PayContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public PayPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void buy(Map<String, Object> map) {
        addSubscribe(mDataManager.postBuy(map)
                .compose(RxUtil.<PayBean>rxSchedulerHelper())
                .map(new Function<PayBean, PayBean>() {
                    @Override
                    public PayBean apply(PayBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<PayBean>(mView) {
                    @Override
                    public void onNext(PayBean bean) {
                        mView.buyFinish(bean);
                    }
                })
        );
    }

    @Override
    public void pay(String orderId, Map<String, Object> map) {
        addSubscribe(mDataManager.putPay(Apis.HOST + Apis.URL_ORDER + "/" + orderId, map)
                .compose(RxUtil.<PayBean>rxSchedulerHelper())
                .map(new Function<PayBean, PayBean>() {
                    @Override
                    public PayBean apply(PayBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<PayBean>(mView) {
                    @Override
                    public void onNext(PayBean bean) {
                        mView.payFinish(bean);
                    }
                })
        );
    }
}
