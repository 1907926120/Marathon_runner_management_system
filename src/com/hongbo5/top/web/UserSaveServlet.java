package com.hongbo5.top.web;

import com.hongbo5.top.dao.UserDao;
import com.hongbo5.top.model.User;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.ResponseUtil;
import com.hongbo5.top.util.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserSaveServlet", value = "/userSave")
public class UserSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        User user = new User(userName,password);
        if (StringUtil.isNotEmpty(id)) {
            user.setId(Integer.parseInt(id));
        }
        Connection con=null;
        try{
            con=dbUtil.getCon();
            int saveNums = 0;
            JSONObject result=new JSONObject();
            if (StringUtil.isNotEmpty(id)) {
                saveNums = userDao.userModify(con, user);//修改记录
            }else {
                //为空 则添加
                saveNums = userDao.userAdd(con, user);//添加记录
            }
            if (saveNums > 0) {
                result.put("success", "true");
            }else{
                //easyui设计的缺陷 需要识别success才能运行
                result.put("success", "true");
                result.put("errorMsg", "操作失败");
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
