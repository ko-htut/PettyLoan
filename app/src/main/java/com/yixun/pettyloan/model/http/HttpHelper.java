package com.yixun.pettyloan.model.http;


import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.HotListBean;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.model.bean.Notice;
import com.yixun.pettyloan.model.bean.NoticesListBean;
import com.yixun.pettyloan.model.bean.PayBean;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.model.bean.ProductsListBean;
import com.yixun.pettyloan.model.bean.RechargeBean;
import com.yixun.pettyloan.model.bean.RegisterBean;
import com.yixun.pettyloan.model.bean.TradeRecordBean;

import java.util.Map;

import io.reactivex.Flowable;

public interface HttpHelper {

    Flowable<HotListBean> fetchHotListInfo();

    Flowable<ProductsListBean> fetchProductsListInfo(Map<String, Integer> map);

    Flowable<BannerBean> fetchBannerInfo(int Channel);

    Flowable<BaseJson<String>> fetchRegisterAuthCode(String phone);

    Flowable<RegisterBean> postRegister(Map<String, Object> map);

    Flowable<CustomerBean> postLogin(Map<String, Object> map);

    Flowable<BaseJson<String>> deleteExit();

    Flowable<PayBean> postBuy(Map<String, Object> map);

    Flowable<PayBean> putPay(String url, Map<String, Object> map);

    Flowable<CustomerBean> putAuth(Map<String, Object> map);

    Flowable<TradeRecordBean> getTradeRecord(Map<String, Object> map);

    Flowable<RechargeBean> postRechargeOrWithdraw(Map<String, Object> map);

    Flowable<ProductDetailBean> getProductDetail(String url);

    Flowable<NoticesListBean> getNoticesList(Map<String, Object> map);

    Flowable<Notice> getNoticeDetail(String url);

    Flowable<CustomerBean> getCustomerInfo();
}
