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
        //����ǰ������
        String jobNo=request.getParameter("jobNo");
        String adminName=request.getParameter("adminName");
        String sex=request.getParameter("sex");
        String bBirthday=request.getParameter("bBirthday");
        String eBirthday=request.getParameter("eBirthday");
        String userId=request.getParameter("userId");
        //bug  ����Ϊȫ�ֱ�����ᵼ�¸������ݿ��ѯ����
        AdminDatagrid adminDatagrid = new AdminDatagrid();
        if (jobNo != null) {
            adminDatagrid.setJobNo(jobNo);
            adminDatagrid.setName(adminName);
            adminDatagrid.setSex(sex);
            //����userId
            if (StringUtil.isNotEmpty(userId)) {
                adminDatagrid.setUserId(Integer.parseInt(userId));
            }
        }

        String page=request.getParameter("page"); //��ǰҳ
        String rows=request.getParameter("rows");//ÿҳ��¼��
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//��װһ����page����
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray= JsonUtil.formatRsToJsonArray(adminDatagridDao.adminList(con, pageBean,adminDatagrid,bBirthday,eBirthday));//ת��ΪJSON����
            int total=adminDatagridDao.adminCount(con,adminDatagrid,bBirthday,eBirthday);//��ȡ�ܼ�¼��
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

