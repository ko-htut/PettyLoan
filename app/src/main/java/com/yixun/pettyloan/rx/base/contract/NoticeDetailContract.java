package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.Notice;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface NoticeDetailContract {
    interface View extends BaseView {
        void showContent(Notice bean);
    }

    interface Presenter extends BasePresenter<View> {
        void getNoticeDetail(String id);
    }
}
