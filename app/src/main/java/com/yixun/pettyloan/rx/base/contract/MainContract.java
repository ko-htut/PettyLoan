package com.yixun.pettyloan.rx.base.contract;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yixun.pettyloan.rx.base.BasePresenter;
import com.yixun.pettyloan.rx.base.BaseView;

/**
 * Created by zongkaili on 2017/8/28.
 */

public interface MainContract {

    interface View extends BaseView {

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    interface  Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);

        void setNightModeState(boolean b);

        void setCurrentItem(int index);

        int getCurrentItem();

        void setVersionPoint(boolean b);

        boolean getVersionPoint();
    }
}
