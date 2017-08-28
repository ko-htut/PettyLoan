package com.yixun.pettyloan;

import android.os.Environment;

import java.io.File;

/**
 * Created by zongkaili on 17-3-26.
 */

public class AppConfig {
    public static final String PREFERENCE_SHOW_SPLASH = "pre_show_splash";
    public static final String PREFERENCE_LOGIN_FLAG = "preference_login_flag";

    public static final String DEBUG_TAG = "logger";

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

}
