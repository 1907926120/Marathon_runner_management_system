package com.hongbo5.top.web;

import com.hongbo5.top.dao.User2Dao;
import com.hongbo5.top.model.User2;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "Login2Servlet", value = "/login2")
public class Login2Servlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    User2Dao user2Dao=new User2Dao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //从服务器获取数据
        String userName=request.getParameter("userName2");
        String password=request.getParameter("password2");
        //提高用户体验，刷新后不重置数据
        request.setAttribute("userName2", userName);
        request.setAttribute("password2", password);
        //服务器验证 安全
        if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
            request.setAttribute("error", "用户名或密码为空！");
            //跳转
            request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            return;
        }
        User2 user=new User2(userName,password);
        Connection con=null;
        try {
            con=dbUtil.getCon();
            User2 currentUser=user2Dao.login(con, user);
            if(currentUser==null){
                request.setAttribute("error", "用户名或密码错误！");
                // 服务器跳转
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }else{
                //获取session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser",currentUser);
                // 客户端跳转
                response.sendRedirect("main2.jsp");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
