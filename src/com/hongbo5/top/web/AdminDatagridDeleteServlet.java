package com.hongbo5.top.web;

import com.hongbo5.top.dao.AdminDatagridDao;
import com.hongbo5.top.model.AdminDatagrid;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AdminDatagridDeleteServlet", value = "/adminDatagridDelete")
public class AdminDatagridDeleteServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    AdminDatagridDao adminDatagridDao = new AdminDatagridDao();
    AdminDatagrid adminDatagrid = new AdminDatagrid();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前台接受的数据
        String delIds = request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums = adminDatagridDao.adminDelete(con, delIds);//删除记录
            if (delNums > 0) {
                result.put("success", "true");
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "删除失败");
            }
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
