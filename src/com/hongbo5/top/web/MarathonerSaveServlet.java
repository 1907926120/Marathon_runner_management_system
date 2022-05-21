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
        //�첽�ύ��������������
        //ajaxĬ��utf-8��ʽ   ҳ���ύ����Ĭ��utf-8   ��TomcatĬ�ϲ��Ǹø�ʽ
        request.setCharacterEncoding("utf-8");
        //ǰ̨���ܵ����� form����������
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String foreignName = request.getParameter("foreignName");
        String nationality = request.getParameter("nationality");
        String birthday = request.getParameter("birthday");
        String stature = request.getParameter("stature");
        String weight = request.getParameter("weight");
        String awards = request.getParameter("awards");
        String id = request.getParameter("id");
        //int��������Ϊ�գ���Ȼ��bbq
        MarathonerDatagrid marathonerDatagrid = new MarathonerDatagrid(name,sex,foreignName,nationality,birthday,Integer.parseInt(stature),Integer.parseInt(weight),awards);
        if (StringUtil.isNotEmpty(id)) {
            marathonerDatagrid.setId(Integer.parseInt(id));
        }
        Connection con=null;
        try{
            con=dbUtil.getCon();
            int saveNums = 0;
            JSONObject result=new JSONObject();
            //��Ϊ�� ���޸�
            if (StringUtil.isNotEmpty(id)) {
                saveNums = marathonerDatagridDao.marathonerModify(con, marathonerDatagrid);//�޸ļ�¼
            }else {
                //Ϊ�� �����
                saveNums = marathonerDatagridDao.marathonerAdd(con, marathonerDatagrid);//��Ӽ�¼
            }
            if (saveNums > 0) {
                result.put("success", "true");
            }else{
                //easyui��Ƶ�ȱ�� ��Ҫʶ��success��������
                result.put("success", "true");
                result.put("errorMsg", "����ʧ��");
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
