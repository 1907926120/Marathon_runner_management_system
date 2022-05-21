package com.hongbo5.top.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    /**
     * 连接数据库
     */
    // ?serverTimezone=GMT
    private String dbUrl="jdbc:mysql://localhost:3306/db_marathonerInfo ?serverTimezone=GMT"; // 数据库连接地址
    private String dbUserName="root"; // 用户名
    private String dbPassword="123456"; // 密码
    private String jdbcName="com.mysql.cj.jdbc.Driver"; // 驱动名称

    /**
     * 获取连接
     * @return
     * @throws Exception
     */
    public Connection getCon()throws Exception{
        Class.forName(jdbcName);
        Connection con= DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }
    /**
     * 关闭数据库
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con)throws Exception{
        if(con!=null){
            con.close();
        }
    }
    public static void main(String[] args) {
        DbUtil dbUtil=new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
