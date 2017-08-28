package com.yixun.pettyloan.model.http.api;
import com.yixun.pettyloan.model.bean.HotListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by codeest on 2016/8/2.
 * 知乎APIs
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 热门日报
     */
    @GET("news/hot")
    Flowable<HotListBean> getHotList();
}
