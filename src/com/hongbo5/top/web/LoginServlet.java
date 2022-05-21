package com.hongbo5.top.web;

import com.hongbo5.top.dao.UserDao;
import com.hongbo5.top.model.User;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet{
    DbUtil dbUtil=new DbUtil();
    UserDao userDao=new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        //提高用户体验，刷新后不重置数据
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        //服务器验证 安全
        if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
            request.setAttribute("error", "The user name or password is empty!");
            //跳转
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        User user=new User(userName,password);
        Connection con=null;
        try {
            con=dbUtil.getCon();
            User currentUser=userDao.login(con, user);
            if(currentUser==null){
                request.setAttribute("error", "ERROR Incorrect username or password!");
                // 服务器跳转
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                //获取session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser",currentUser);
                // 客户端跳转
                response.sendRedirect("main.jsp");
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
