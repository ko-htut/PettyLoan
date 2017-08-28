/**
 * 项目名称: IDSeeAR
 * 类名称:  IdseeARApplication
 * 类描述:
 * 创建人: Ly
 * 创建时间: 2013-1-23 上午10:11:14
 * 修改人:
 * 修改时间:
 * 备注:
 *
 * @version
 */
package com.yixun.pettyloan;

import android.app.Application;
import android.content.Context;
import android.os.PowerManager;

import com.yixun.pettyloan.rx.di.component.AppComponent;
import com.yixun.pettyloan.rx.di.module.AppModule;
import com.yixun.pettyloan.rx.di.module.HttpModule;

public class App extends Application {
    public static Context applicationContext;
    private static App mInstance = null;
    private PowerManager.WakeLock mWakeLock;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        applicationContext = getApplicationContext();
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Idealsee_AR");

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onTerminate() {
        if (mWakeLock.isHeld()) {
            mWakeLock.release();
        }
        super.onTerminate();
    }

    public static App getInstance() {
        return mInstance;
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
