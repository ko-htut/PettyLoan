package com.yixun.pettyloan.model.http.api;

import com.google.gson.JsonObject;
import com.yixun.pettyloan.model.bean.BannerBean;
import com.yixun.pettyloan.model.bean.BaseJson;
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
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by zongkaili on 2017/8/31.
 */

public interface Apis {
//    String HOST = "http://10.0.1.237:5000";
    String HOST = "http://xed.arhieason.com";
    String RequestSuccess = "success";
    String URL_PRODUCT = "/api/product";
    String URL_ORDER = "/api/order";
    String URL_NOTICE = "/api/notice";

    @GET("/api/product")
    Flowable<ProductsListBean> getProductsList(@QueryMap Map<String, Integer> maps);

    @GET("/api/banner")
    Flowable<BannerBean> getBannerList(@Query("channel") int channel);

    @GET("/api/customer/register")
    Flowable<BaseJson<String>> getRegisterAuthCode(@Query("phone") String phone);

    @Headers("Content-Type:application/json")
    @POST("/api/customer/register")
    Flowable<RegisterBean> postRegister(@Body JsonObject body);

    @Headers("Content-Type:application/json")
    @POST("/api/login")
    Flowable<CustomerBean> postLogin(@Body JsonObject body);

    @DELETE("/api/login")
    Flowable<BaseJson<String>> deleteExit();

    @Headers("Content-Type:application/json")
    @POST("/api/order")
    Flowable<PayBean> postBuy(@Body JsonObject body);

    @Headers("Content-Type:application/json")
    @PUT
    Flowable<PayBean> putPay(@Url String url, @Body JsonObject body);

    @Headers("Content-Type:application/json")
    @PUT("/api/customer")
    Flowable<CustomerBean> putAuth(@Body JsonObject body);

    @Headers("Content-Type:application/json")
    @POST("/api/order")
    Flowable<RechargeBean> postRechargeOrWithdraw(@Body JsonObject body);

    @GET("/api/trade_record")
    Flowable<TradeRecordBean> getTradeRecord(@QueryMap Map<String, Object> maps);

    @GET
    Flowable<ProductDetailBean> getProductDetail(@Url String url);

    @GET("/api/notice")
    Flowable<NoticesListBean> getNoticesList(@QueryMap Map<String, Object> maps);

    @GET
    Flowable<Notice> getNoticeDetail(@Url String url);

    @GET("/api/customer")
    Flowable<CustomerBean> getCustomerInfo();

}
