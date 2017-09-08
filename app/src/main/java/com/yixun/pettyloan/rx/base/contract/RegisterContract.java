package com.yixun.pettyloan.rx.base.contract;

import com.yixun.pettyloan.model.bean.BaseJson;
import com.yixun.pettyloan.model.bean.RegisterBean;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

import java.util.Map;

/**
 * Created by zongkaili on 2017/8/29.
 */

public interface RegisterContract {

    interface View extends BaseView {

        void showContent(BaseJson<String> bean);

        void registerFinish(RegisterBean registerBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getAuthCode(String phone);

        void register(Map<String, Object> map);

    }
}
