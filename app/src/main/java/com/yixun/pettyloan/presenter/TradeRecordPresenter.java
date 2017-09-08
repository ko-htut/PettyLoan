package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.TradeRecordBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.TradeRecordContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class TradeRecordPresenter extends RxPresenter<TradeRecordContract.View> implements TradeRecordContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public TradeRecordPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getTradeRecord(Map<String, Object> map) {
        addSubscribe(mDataManager.getTradeRecord(map)
                .compose(RxUtil.<TradeRecordBean>rxSchedulerHelper())
                .map(new Function<TradeRecordBean, TradeRecordBean>() {
                    @Override
                    public TradeRecordBean apply(TradeRecordBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<TradeRecordBean>(mView) {
                    @Override
                    public void onNext(TradeRecordBean bean) {
                        mView.showContent(bean);
                    }
                })
        );
    }
}
