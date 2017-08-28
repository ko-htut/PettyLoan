package com.yixun.pettyloan.model.http;


import com.yixun.pettyloan.model.bean.HotListBean;
import com.yixun.pettyloan.model.http.api.ZhihuApis;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper implements HttpHelper {

    private ZhihuApis mZhihuApiService;

    @Inject
    public RetrofitHelper(ZhihuApis zhihuApiService) {
        this.mZhihuApiService = zhihuApiService;
    }

    @Override
    public Flowable<HotListBean> fetchHotListInfo() {
        return mZhihuApiService.getHotList();
    }
}
