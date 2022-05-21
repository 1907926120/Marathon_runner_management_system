package com.hongbo5.top.web;

import com.hongbo5.top.dao.MarathonerDatagridDao;
import com.hongbo5.top.model.MarathonerDatagrid;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.ResponseUtil;
import com.hongbo5.top.util.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "MarathonerSaveServlet", value = "/marathonerSave")
public class MarathonerSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    MarathonerDatagridDao marathonerDatagridDao = new MarathonerDatagridDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //异步提交中文乱码问题解决
        //ajax默认utf-8格式   页面提交数据默认utf-8   而Tomcat默认不是该格式
        request.setCharacterEncoding("utf-8");
        //前台接受的数据 form过来的数据
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String foreignName = request.getParameter("foreignName");
        String nationality = request.getParameter("nationality");
        String birthday = request.getParameter("birthday");
        String stature = request.getParameter("stature");
        String weight = request.getParameter("weight");
        String awards = request.getParameter("awards");
        String id = request.getParameter("id");
        //int参数不能为空，不然就bbq
        MarathonerDatagrid marathonerDatagrid = new MarathonerDatagrid(name,sex,foreignName,nationality,birthday,Integer.parseInt(stature),Integer.parseInt(weight),awards);
        if (StringUtil.isNotEmpty(id)) {
            marathonerDatagrid.setId(Integer.parseInt(id));
        }
        Connection con=null;
        try{
            con=dbUtil.getCon();
            int saveNums = 0;
            JSONObject result=new JSONObject();
            //不为空 则修改
            if (StringUtil.isNotEmpty(id)) {
                saveNums = marathonerDatagridDao.marathonerModify(con, marathonerDatagrid);//修改记录
            }else {
                //为空 则添加
                saveNums = marathonerDatagridDao.marathonerAdd(con, marathonerDatagrid);//添加记录
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
