package com.hongbo5.top.web;

import com.hongbo5.top.dao.AdminDatagridDao;
import com.hongbo5.top.dao.UserDao;
import com.hongbo5.top.model.User;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserDeleteServlet", value = "/userDelete")
public class UserDeleteServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    AdminDatagridDao adminDatagridDao = new AdminDatagridDao();
    User user = new User();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();

            //传来以逗号为间隔的数据
            String str[] = delIds.split(",");
            //因为删除有可能是多条数据 则挨个判断
            for (int i = 0; i < str.length; i++) {
                boolean f = adminDatagridDao.getAdminByUserId(con, str[i]);
                if (f) {
                    //提高用户体验 找到那条数据不能删除
                    result.put("errorIndex",i);

                    result.put("errorMsg","账号下面有管理员信息，不能删除!");
                    ResponseUtil.write(response, result);
                    //return这个函数直接结束，不会向下进行
                    return;
                }
            }

            int delNums = userDao.userDelete(con, delIds);//删除记录
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
