package com.yixun.pettyloan.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by zongkaili on 17-9-6.
 */

public class NumberUtils {
    /**
     * float保留两位小数
     *
     * @param num
     * @param places
     * @return
     */
    public static float keepDecimalPlaces(float num, int places) {
        int newPlaces = 10 * places;
        return (float) (Math.round(num * newPlaces) / newPlaces);
    }

    /**
     * 格式化number,分组显示
     * 如：传入88888888.888888888或者科学计数法的数字
     * 　　输出88,888,888.89
     *
     * @param num
     * @param places
     * @return
     */
    public static String formatNumber(double num, int places) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.setGroupingUsed(true);
        df.setMaximumFractionDigits(places);
        return df.format(num);
    }

}
