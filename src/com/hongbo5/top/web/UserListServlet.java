package com.hongbo5.top.web;

import com.hongbo5.top.dao.UserDao;
import com.hongbo5.top.dao.UserDao;;
import com.hongbo5.top.model.User;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.model.User;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.JsonUtil;
import com.hongbo5.top.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserListServlet", value = "/userList")
public class UserListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    User user = new User();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ǰ̨���ܵ�����
        String page=request.getParameter("page"); //��ǰҳ
        String rows=request.getParameter("rows");//ÿҳ��¼��
        String userUserName = request.getParameter("userName");
        if (userUserName == null) {
            userUserName = "";
        }
        user.setUserName(userUserName);
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//��װһ����page����
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(userDao.userList(con, pageBean,user));//ת��ΪJSON����
            int total=userDao.userCount(con,user);//��ȡ�ܼ�¼��
            //���ܵı��ǡ�rows������total��
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
