package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.Notice;
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.NoticeDetailContract;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class NoticeDetailPresenter extends RxPresenter<NoticeDetailContract.View> implements NoticeDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NoticeDetailPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getNoticeDetail(String noticeId) {
        addSubscribe(mDataManager.getNoticeDetail(Apis.HOST + Apis.URL_PRODUCT + "/" + noticeId)
                .compose(RxUtil.<Notice>rxSchedulerHelper())
                .map(new Function<Notice, Notice>() {
                    @Override
                    public Notice apply(Notice bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<Notice>(mView) {
                    @Override
                    public void onNext(Notice bean) {
                        mView.showContent(bean);
                    }
                })
        );
    }
}
