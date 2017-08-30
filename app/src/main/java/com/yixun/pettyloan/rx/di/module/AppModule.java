package com.yixun.pettyloan.rx.di.module;

import com.yixun.pettyloan.App;
import com.yixun.pettyloan.model.DataManager;
import com.yixun.pettyloan.model.db.DBHelper;
import com.yixun.pettyloan.model.db.RealmHelper;
import com.yixun.pettyloan.model.http.HttpHelper;
import com.yixun.pettyloan.model.http.RetrofitHelper;
import com.yixun.pettyloan.model.prefs.ImplPreferencesHelper;
import com.yixun.pettyloan.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
