package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.RechargeBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.RechargeContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class RechargePresenter extends RxPresenter<RechargeContract.View> implements RechargeContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RechargePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void rechangeOrWithdraw(Map<String, Object> map) {
        addSubscribe(mDataManager.postRechargeOrWithdraw(map)
                .compose(RxUtil.<RechargeBean>rxSchedulerHelper())
                .map(new Function<RechargeBean, RechargeBean>() {
                    @Override
                    public RechargeBean apply(RechargeBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<RechargeBean>(mView) {
                    @Override
                    public void onNext(RechargeBean bean) {
                        mView.rechangeOrWithdrawFinish(bean);
                    }
                })
        );
    }
}
