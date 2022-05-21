package com.hongbo5.top.web;

import com.hongbo5.top.dao.AdminDatagridDao;
import com.hongbo5.top.model.AdminDatagrid;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.JsonUtil;
import com.hongbo5.top.util.ResponseUtil;
import com.hongbo5.top.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AdminDatagridListServlet", value = "/adminDatagridList")
public class AdminDatagridListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    AdminDatagridDao adminDatagridDao = new AdminDatagridDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受前端数据
        String jobNo=request.getParameter("jobNo");
        String adminName=request.getParameter("adminName");
        String sex=request.getParameter("sex");
        String bBirthday=request.getParameter("bBirthday");
        String eBirthday=request.getParameter("eBirthday");
        String userId=request.getParameter("userId");
        //bug  若变为全局变量则会导致复杂数据库查询故障
        AdminDatagrid adminDatagrid = new AdminDatagrid();
        if (jobNo != null) {
            adminDatagrid.setJobNo(jobNo);
            adminDatagrid.setName(adminName);
            adminDatagrid.setSex(sex);
            //处理userId
            if (StringUtil.isNotEmpty(userId)) {
                adminDatagrid.setUserId(Integer.parseInt(userId));
            }
        }

        String page=request.getParameter("page"); //当前页
        String rows=request.getParameter("rows");//每页记录数
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//封装一个人page对象
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray= JsonUtil.formatRsToJsonArray(adminDatagridDao.adminList(con, pageBean,adminDatagrid,bBirthday,eBirthday));//转换为JSON数据
            int total=adminDatagridDao.adminCount(con,adminDatagrid,bBirthday,eBirthday);//获取总记录数
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

