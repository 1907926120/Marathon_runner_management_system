package com.hongbo5.top.web;

import com.hongbo5.top.dao.UserDatagridDao;
import com.hongbo5.top.model.UserDatagrid;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.JsonUtil;
import com.hongbo5.top.util.ResponseUtil;
import com.hongbo5.top.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserDatagridListServlet", value = "/userDatagridList")
public class UserDatagridListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDatagridDao userDatagridDao = new UserDatagridDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受前端数据
        String jobNo=request.getParameter("jobNo");
        String user2Name=request.getParameter("user2Name");
        String sex=request.getParameter("sex");
        String bBirthday=request.getParameter("bBirthday");
        String eBirthday=request.getParameter("eBirthday");
        String user2Id=request.getParameter("user2Id");
        //bug  若变为全局变量则会导致复杂数据库查询故障
        UserDatagrid userDatagrid = new UserDatagrid();
        if (jobNo != null) {
            userDatagrid.setJobNo(jobNo);
            userDatagrid.setName(user2Name);
            userDatagrid.setSex(sex);
            //处理user2Id
            if (StringUtil.isNotEmpty(user2Id)) {
                userDatagrid.setUser2Id(Integer.parseInt(user2Id));
            }
        }

        String page=request.getParameter("page"); //当前页
        String rows=request.getParameter("rows");//每页记录数
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//封装一个人page对象
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray= JsonUtil.formatRsToJsonArray(userDatagridDao.userList(con, pageBean,userDatagrid,bBirthday,eBirthday));//转换为JSON数据
            int total=userDatagridDao.userCount(con,userDatagrid,bBirthday,eBirthday);//获取总记录数
            //接受的便是“rows”，“total”
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(response, result);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

