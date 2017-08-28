package com.yixun.pettyloan;

import com.yixun.pettyloan.model.bean.HotListBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

/**
 * Created by codeest on 16/8/12.
 */

public interface HotContract {

    interface View extends BaseView {

        void showContent(HotListBean hotListBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getHotData();

        void insertReadToDB(int id);

    }
}
