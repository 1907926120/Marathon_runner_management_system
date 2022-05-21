package com.hongbo5.top.web;

import com.hongbo5.top.dao.User2Dao;
import com.hongbo5.top.dao.UserDao;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.model.User2;
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

;

@WebServlet(name = "User2ListServlet", value = "/user2List")
public class User2ListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    User2Dao user2Dao = new User2Dao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ǰ̨���ܵ�����
        String page=request.getParameter("page"); //��ǰҳ
        String rows=request.getParameter("rows");//ÿҳ��¼��
        String user2UserName = request.getParameter("userName");
        if (user2UserName == null) {
            user2UserName = "";
        }
        User2 user2 = new User2();
        user2.setUserName(user2UserName);
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//��װһ����page����
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(user2Dao.user2List(con, pageBean,user2));//ת��ΪJSON����
            int total=user2Dao.user2Count(con,user2);//��ȡ�ܼ�¼��
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
