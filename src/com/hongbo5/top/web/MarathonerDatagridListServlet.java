package com.hongbo5.top.web;

import com.hongbo5.top.dao.MarathonerDatagridDao;
import com.hongbo5.top.model.MarathonerDatagrid;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.JsonUtil;
import com.hongbo5.top.util.ResponseUtil;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name = "MarathonerDatagridListServlet", value = "/marathonerDatagridList")
public class MarathonerDatagridListServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    MarathonerDatagridDao marathonerDatagridDao = new MarathonerDatagridDao();
    MarathonerDatagrid marathonerDatagrid = new MarathonerDatagrid();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ǰ̨���ܵ�����
        String page=request.getParameter("page"); //��ǰҳ
        String rows=request.getParameter("rows");//ÿҳ��¼��
        String marathonerName = request.getParameter("name");
        if (marathonerName == null) {
            marathonerName = "";
        }
        marathonerDatagrid.setName(marathonerName);
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));//��װһ����page����
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray=JsonUtil.formatRsToJsonArray(marathonerDatagridDao.marathonerList(con, pageBean,marathonerDatagrid));//ת��ΪJSON����
            int total=marathonerDatagridDao.marathonerCount(con,marathonerDatagrid);//��ȡ�ܼ�¼��
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
