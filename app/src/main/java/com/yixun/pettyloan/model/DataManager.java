package com.yixun.pettyloan.model;


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
import com.yixun.pettyloan.model.db.DBHelper;
import com.yixun.pettyloan.model.http.HttpHelper;
import com.yixun.pettyloan.model.prefs.PreferencesHelper;

import java.util.Map;

import io.reactivex.Flowable;

public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferencesHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferencesHelper.setAutoCacheState(state);
    }

    @Override
    public int getCurrentItem() {
        return mPreferencesHelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mPreferencesHelper.setCurrentItem(item);
    }

    @Override
    public boolean getLikePoint() {
        return mPreferencesHelper.getLikePoint();
    }

    @Override
    public void setLikePoint(boolean isFirst) {
        mPreferencesHelper.setLikePoint(isFirst);
    }

    @Override
    public boolean getVersionPoint() {
        return mPreferencesHelper.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean isFirst) {
        mPreferencesHelper.setVersionPoint(isFirst);
    }

    @Override
    public boolean getManagerPoint() {
        return mPreferencesHelper.getManagerPoint();
    }

    @Override
    public void setManagerPoint(boolean isFirst) {
        mPreferencesHelper.setManagerPoint(isFirst);
    }

    @Override
    public void insertNewsId(int id) {
        mDbHelper.insertNewsId(id);
    }

    @Override
    public boolean queryNewsId(int id) {
        return mDbHelper.queryNewsId(id);
    }

    @Override
    public Flowable<HotListBean> fetchHotListInfo() {
        return mHttpHelper.fetchHotListInfo();
    }

    //-----------------------------------
    @Override
    public Flowable<ProductsListBean> fetchProductsListInfo(Map<String, Integer> map) {
        return mHttpHelper.fetchProductsListInfo(map);
    }

    @Override
    public Flowable<BannerBean> fetchBannerInfo(int channel) {
        return mHttpHelper.fetchBannerInfo(channel);
    }

    @Override
    public Flowable<BaseJson<String>> fetchRegisterAuthCode(String phone) {
        return mHttpHelper.fetchRegisterAuthCode(phone);
    }

    @Override
    public Flowable<RegisterBean> postRegister(Map<String, Object> map) {
        return mHttpHelper.postRegister(map);
    }

    @Override
    public Flowable<CustomerBean> postLogin(Map<String, Object> map) {
        return mHttpHelper.postLogin(map);
    }

    @Override
    public Flowable<BaseJson<String>> deleteExit() {
        return mHttpHelper.deleteExit();
    }

    @Override
    public Flowable<PayBean> postBuy(Map<String, Object> map) {
        return mHttpHelper.postBuy(map);
    }

    @Override
    public Flowable<PayBean> putPay(String url, Map<String, Object> map) {
        return mHttpHelper.putPay(url, map);
    }

    @Override
    public Flowable<CustomerBean> putAuth(Map<String, Object> map) {
        return  mHttpHelper.putAuth(map);
    }

    @Override
    public Flowable<TradeRecordBean> getTradeRecord(Map<String, Object> map) {
        return mHttpHelper.getTradeRecord(map);
    }

    @Override
    public Flowable<RechargeBean> postRechargeOrWithdraw(Map<String, Object> map) {
        return mHttpHelper.postRechargeOrWithdraw(map);
    }

    @Override
    public Flowable<ProductDetailBean> getProductDetail(String url) {
        return mHttpHelper.getProductDetail(url);
    }

    @Override
    public Flowable<NoticesListBean> getNoticesList(Map<String, Object> map) {
        return mHttpHelper.getNoticesList(map);
    }

    @Override
    public Flowable<Notice> getNoticeDetail(String url) {
        return mHttpHelper.getNoticeDetail(url);
    }

    @Override
    public Flowable<CustomerBean> getCustomerInfo() {
        return mHttpHelper.getCustomerInfo();
    }

}
