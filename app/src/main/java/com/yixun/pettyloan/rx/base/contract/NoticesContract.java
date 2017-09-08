package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.NoticesListBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface NoticesContract {
    interface View extends BaseView {
        void showContent(NoticesListBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void getNoticesList(Map<String, Object> map);
    }
}
