package com.yixun.pettyloan.model.http;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import com.yixun.pettyloan.model.http.api.Apis;
import com.yixun.pettyloan.model.http.api.ZhihuApis;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Created by zongkaili on 2017/8/29.
 */
public class RetrofitHelper implements HttpHelper {

    private ZhihuApis mZhihuApiService;
    private Apis mApiService;

    @Inject
    public RetrofitHelper(ZhihuApis zhihuApiService, Apis apiService) {
        this.mZhihuApiService = zhihuApiService;
        this.mApiService = apiService;
    }

    @Override
    public Flowable<HotListBean> fetchHotListInfo() {
        return mZhihuApiService.getHotList();
    }

    @Override
    public Flowable<ProductsListBean> fetchProductsListInfo(Map<String, Integer> map) {
        return mApiService.getProductsList(map);
    }

    @Override
    public Flowable<BannerBean> fetchBannerInfo(int channel) {
        return mApiService.getBannerList(channel);
    }

    @Override
    public Flowable<BaseJson<String>> fetchRegisterAuthCode(String phone) {
        return mApiService.getRegisterAuthCode(phone);
    }

    @Override
    public Flowable<RegisterBean> postRegister(Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return mApiService.postRegister(jsonObject);
    }

    @Override
    public Flowable<CustomerBean> postLogin(Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return mApiService.postLogin(jsonObject);
    }

    @Override
    public Flowable<BaseJson<String>> deleteExit() {
        return mApiService.deleteExit();
    }

    @Override
    public Flowable<PayBean> postBuy(Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return mApiService.postBuy(jsonObject);
    }

    @Override
    public Flowable<PayBean> putPay(String url, Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return mApiService.putPay(url, jsonObject);
    }

    @Override
    public Flowable<CustomerBean> putAuth(Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return mApiService.putAuth(jsonObject);
    }

    @Override
    public Flowable<TradeRecordBean> getTradeRecord(Map<String, Object> map) {
        return mApiService.getTradeRecord(map);
    }

    @Override
    public Flowable<RechargeBean> postRechargeOrWithdraw(Map<String, Object> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return mApiService.postRechargeOrWithdraw(jsonObject);
    }

    @Override
    public Flowable<ProductDetailBean> getProductDetail(String url) {
        return mApiService.getProductDetail(url);
    }

    @Override
    public Flowable<NoticesListBean> getNoticesList(Map<String, Object> map) {
        return mApiService.getNoticesList(map);
    }

    @Override
    public Flowable<Notice> getNoticeDetail(String url) {
        return mApiService.getNoticeDetail(url);
    }

    @Override
    public Flowable<CustomerBean> getCustomerInfo() {
        return mApiService.getCustomerInfo();
    }
}
