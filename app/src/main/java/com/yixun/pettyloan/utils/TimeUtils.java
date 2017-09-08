package com.yixun.pettyloan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zongkaili on 17-9-5.
 */

public class TimeUtils {

    private static final String TAG = TimeUtils.class.getSimpleName();

    /**
     * 时间字符串转化为时间戳
     * @param dateStr "yyyy-MM-dd HH:mm:ss"格式的时间字符串
     * @return 毫秒时间戳
     */
    public static long str2date(String dateStr){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = simpleDateFormat .parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.logd(TAG," date.getTime(): " + date.getTime());
        return date.getTime();
    }

}
