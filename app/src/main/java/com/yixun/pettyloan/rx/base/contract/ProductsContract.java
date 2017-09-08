package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface ProductsContract {

    interface View extends BaseView {

        void showContent(ProductsListBean productsListBean);
        void showTopBannerContent(BannerBean bannerBean);
        void showFeedBannerContent(BannerBean bannerBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getProductsData(Map<String,Integer> map);
        void getBannerData(int channel);
        void insertReadToDB(int id);

    }
}
