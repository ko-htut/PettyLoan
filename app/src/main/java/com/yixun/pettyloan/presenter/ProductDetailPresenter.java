package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.ProductDetailContract;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class ProductDetailPresenter extends RxPresenter<ProductDetailContract.View> implements ProductDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ProductDetailPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getProductDetail(int productId) {
        addSubscribe(mDataManager.getProductDetail(Apis.HOST + Apis.URL_PRODUCT + "/" + productId)
                .compose(RxUtil.<ProductDetailBean>rxSchedulerHelper())
                .map(new Function<ProductDetailBean, ProductDetailBean>() {
                    @Override
                    public ProductDetailBean apply(ProductDetailBean bean) {
                        return bean;
                    }
                })
                .subscribeWith(new CommonSubscriber<ProductDetailBean>(mView) {
                    @Override
                    public void onNext(ProductDetailBean bean) {
                        mView.showContent(bean);
                    }
                })
        );
    }
}
