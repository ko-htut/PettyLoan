package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.NoticesListBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.NoticesContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class NoticesPresenter extends RxPresenter<NoticesContract.View> implements NoticesContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NoticesPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getNoticesList(Map<String, Object> map) {
        addSubscribe(mDataManager.getNoticesList(map)
                .compose(RxUtil.<NoticesListBean>rxSchedulerHelper())
                .map(new Function<NoticesListBean, NoticesListBean>() {
                    @Override
                    public NoticesListBean apply(NoticesListBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<NoticesListBean>(mView) {
                    @Override
                    public void onNext(NoticesListBean bean) {
                        mView.showContent(bean);
                    }
                })
        );
    }
}
