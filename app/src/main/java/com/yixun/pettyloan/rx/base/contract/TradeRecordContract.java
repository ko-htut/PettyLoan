package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.TradeRecordBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface TradeRecordContract {

    interface View extends BaseView {

        void showContent(TradeRecordBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void getTradeRecord(Map<String, Object> map);
    }
}
