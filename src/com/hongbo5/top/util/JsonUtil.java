package com.hongbo5.top.util;
//引入Json lib
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class JsonUtil {
    public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
        //设置纵向的数据
        ResultSetMetaData md=rs.getMetaData();
        int num=md.getColumnCount();
        JSONArray array=new JSONArray();
        //横向遍历
        while(rs.next()){
            JSONObject mapOfColValues=new JSONObject();
            //纵向遍历
            for(int i=1;i<=num;i++){
                //Json其实是字符串  遇见日期会报错
                //对象转日期
                //判断对象类型 instanceof
                Object o = rs.getObject(i);
                if (o instanceof Date) {
                    mapOfColValues.put(md.getColumnName(i), DateUtil.formatDate((java.util.Date) o, "yyyy-MM-dd"));
                } else {
                    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
                }
            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
