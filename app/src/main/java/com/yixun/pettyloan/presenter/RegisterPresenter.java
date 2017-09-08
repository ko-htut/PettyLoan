package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.RegisterBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.RegisterContract;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getAuthCode(String phone) {
            addSubscribe(mDataManager.fetchRegisterAuthCode(phone)
                    .compose(RxUtil.<BaseJson<String>>rxSchedulerHelper())
                    .map(new Function<BaseJson<String>, BaseJson<String>>() {
                        @Override
                        public BaseJson<String> apply(BaseJson<String> authCodeBean) {
                            return authCodeBean;
                        }
                    })
                    .subscribeWith(new CommonSubscriber<BaseJson<String>>(mView) {
                        @Override
                        public void onNext(BaseJson<String> authCodeBean) {
                            mView.showContent(authCodeBean);
                        }
                    })
            );
    }

    @Override
    public void register(Map<String, Object> map) {
        addSubscribe(mDataManager.postRegister(map)
                .compose(RxUtil.<RegisterBean>rxSchedulerHelper())
                .map(new Function<RegisterBean, RegisterBean>() {
                    @Override
                    public RegisterBean apply(RegisterBean registerBean) {
                        return registerBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<RegisterBean>(mView) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        mView.registerFinish(registerBean);
                    }
                })
        );
    }
}
