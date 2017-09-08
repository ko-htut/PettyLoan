package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;


/**
 * Created by zongkaili on 2017/8/29.
 */

public interface ProductDetailContract {

    interface View extends BaseView {

        void showContent(ProductDetailBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void getProductDetail(int productId);
    }
}
