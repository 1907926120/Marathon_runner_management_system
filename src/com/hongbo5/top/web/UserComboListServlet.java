package com.hongbo5.top.web;

import com.hongbo5.top.dao.UserDao;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.JsonUtil;
import com.hongbo5.top.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UserComboListServlet", value = "/userComboList")
public class UserComboListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONArray jsonArray = new JSONArray();
            //jsonObject��Ӽ�ֵ�� ��ѡ��...
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id","");
            jsonObject.put("userName", "��ѡ��...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(userDao.userList(con, null, null)));//ת��ΪJSON����
            //��ǰ̨����
            ResponseUtil.write(response, jsonArray);
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

