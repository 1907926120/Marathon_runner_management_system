package com.hongbo5.top.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    //日期数据类型装换为字符串
    public static String formatDate(Date date, String format) {
        String result = "";
        //SimpleDateFormat的使用
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }  //字符串装换为日期数据类型
    public static Date formatString( String str,String format) throws Exception {
        //SimpleDateFormat的使用
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}
