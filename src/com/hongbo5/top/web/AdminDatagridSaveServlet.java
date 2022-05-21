package com.hongbo5.top.web;

import com.hongbo5.top.dao.AdminDatagridDao;
import com.hongbo5.top.dao.MarathonerDatagridDao;
import com.hongbo5.top.model.AdminDatagrid;
import com.hongbo5.top.model.MarathonerDatagrid;
import com.hongbo5.top.util.DateUtil;
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
import java.util.Date;

@WebServlet(name = "AdminDatagridSaveServlet", value = "/adminDatagridSave")
public class AdminDatagridSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    AdminDatagridDao adminDatagridDao = new AdminDatagridDao();
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
        String jobNo = request.getParameter("jobNo");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String userId = request.getParameter("userId");
        String email = request.getParameter("email");
        String synopses = request.getParameter("synopses");
        String adminId = request.getParameter("adminId");


        //int��������Ϊ�գ���Ȼ��bbq
        AdminDatagrid adminDatagrid = null;
        try {
            adminDatagrid = new AdminDatagrid(jobNo,name, sex, DateUtil.formatString(birthday,"yyyy-MM-dd"),
                    Integer.parseInt(userId), email,synopses);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (StringUtil.isNotEmpty(adminId)) {
            adminDatagrid.setAdminId(Integer.parseInt(adminId));
        }
        Connection con=null;
        try{
            con=dbUtil.getCon();
            int saveNums = 0;
            JSONObject result=new JSONObject();
            //��Ϊ�� ���޸�
            if (StringUtil.isNotEmpty(adminId)) {
                saveNums = adminDatagridDao.adminModify(con, adminDatagrid);//�޸ļ�¼
            }else {
                //Ϊ�� �����
                saveNums = adminDatagridDao.adminAdd(con, adminDatagrid);//��Ӽ�¼
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
