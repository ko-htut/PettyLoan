package com.yixun.pettyloan.rx.di.component;

import com.yixun.pettyloan.App;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.db.RealmHelper;
import com.yixun.pettyloan.model.http.RetrofitHelper;
import com.yixun.pettyloan.model.prefs.ImplPreferencesHelper;
import com.yixun.pettyloan.rx.di.module.AppModule;
import com.yixun.pettyloan.rx.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zongkaili on 2017/8/29.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
