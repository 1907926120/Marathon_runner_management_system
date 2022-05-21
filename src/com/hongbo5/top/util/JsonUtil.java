package com.hongbo5.top.util;
//����Json lib
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class JsonUtil {
    public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
        //�������������
        ResultSetMetaData md=rs.getMetaData();
        int num=md.getColumnCount();
        JSONArray array=new JSONArray();
        //�������
        while(rs.next()){
            JSONObject mapOfColValues=new JSONObject();
            //�������
            for(int i=1;i<=num;i++){
                //Json��ʵ���ַ���  �������ڻᱨ��
                //����ת����
                //�ж϶������� instanceof
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
