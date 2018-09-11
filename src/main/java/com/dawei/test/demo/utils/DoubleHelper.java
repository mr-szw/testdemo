package com.dawei.test.demo.utils;

import java.text.DecimalFormat;

/**
 * @author by Dawei on 2018/8/29.
 */
public class DoubleHelper {


    public static final String PATTERN_TWO = "######0.00";


    /**
     * 将数据保留两位小数
     * 返回 Str
     */
    public static String formatDoublePatternStr(Double doubleNum, String pattern) {
        if(doubleNum != null && pattern != null) {
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            return decimalFormat.format(doubleNum);
        }
        return null;
    }
    /**
     * 将数据保留两位小数
     * 返回Str
     */
    public static Double formatDoublePattern(Double doubleNum, String pattern) {
        String doublePattern = formatDoublePatternStr(doubleNum, pattern);
        if(doublePattern != null) {
            return Double.valueOf(doublePattern);
        }
        return null;
    }


}
