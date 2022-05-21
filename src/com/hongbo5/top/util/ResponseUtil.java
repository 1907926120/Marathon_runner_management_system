package com.hongbo5.top.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 向页面输出
 */
    public class ResponseUtil {
//    public static void write(HttpServletResponse response,JSONObject jsonObject)throws Exception
    public static void write(HttpServletResponse response,Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        //IO流
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
