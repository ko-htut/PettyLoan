package com.yixun.pettyloan.model.http;


import com.yixun.pettyloan.model.bean.HotListBean;

import io.reactivex.Flowable;

public interface HttpHelper {

    Flowable<HotListBean> fetchHotListInfo();
}
