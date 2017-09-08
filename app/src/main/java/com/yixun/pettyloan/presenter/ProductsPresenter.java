package com.yixun.pettyloan.presenter;


import com.yixun.pettyloan.model.CommonSubscriber;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.rx.RxPresenter;
import com.yixun.pettyloan.rx.RxUtil;
import com.yixun.pettyloan.rx.base.contract.ProductsContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by zongkaili on 2017/8/28.
 */

public class ProductsPresenter extends RxPresenter<ProductsContract.View> implements ProductsContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ProductsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getProductsData(Map<String, Integer> map) {
        addSubscribe(mDataManager.fetchProductsListInfo(map)
                .compose(RxUtil.<ProductsListBean>rxSchedulerHelper())
                .map(new Function<ProductsListBean, ProductsListBean>() {
                    @Override
                    public ProductsListBean apply(ProductsListBean productsListBean) {
                        return productsListBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<ProductsListBean>(mView) {
                    @Override
                    public void onNext(ProductsListBean productsListBean) {
                        mView.showContent(productsListBean);
                    }
                })
        );
    }

    @Override
    public void getBannerData(int channel) {
        addSubscribe(mDataManager.fetchBannerInfo(channel)
                .compose(RxUtil.<BannerBean>rxSchedulerHelper())
                .map(new Function<BannerBean, BannerBean>() {
                    @Override
                    public BannerBean apply(BannerBean bannerBean) {
                        return bannerBean;
                    }
                })
                .subscribeWith(new CommonSubscriber<BannerBean>(mView) {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<BannerBean.Data.Banner> bannerList = bannerBean.getData().getBannerList();
                        if (bannerList.isEmpty())
                            return;
                        if (bannerBean.getData().getBannerList().get(0).getBannerChannel() == 1)
                            mView.showFeedBannerContent(bannerBean);
                        else
                            mView.showTopBannerContent(bannerBean);

                    }
                })
        );
    }

    @Override
    public void insertReadToDB(int id) {
        mDataManager.insertNewsId(id);
    }
}
