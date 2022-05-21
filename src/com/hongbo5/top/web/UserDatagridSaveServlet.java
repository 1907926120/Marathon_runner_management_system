package com.hongbo5.top.web;

import com.hongbo5.top.dao.UserDatagridDao;
import com.hongbo5.top.model.UserDatagrid;
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

@WebServlet(name = "UserDatagridSaveServlet", value = "/userDatagridSave")
public class UserDatagridSaveServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    UserDatagridDao userDatagridDao = new UserDatagridDao();
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
        String user2Id = request.getParameter("user2Id");
        String email = request.getParameter("email");
        String synopses = request.getParameter("synopses");
        String userId = request.getParameter("userId");


        //int��������Ϊ�գ���Ȼ��bbq
        UserDatagrid userDatagrid = null;
        try {
            userDatagrid = new UserDatagrid(jobNo,name, sex, DateUtil.formatString(birthday,"yyyy-MM-dd"),
                    Integer.parseInt(user2Id), email,synopses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtil.isNotEmpty(userId)) {
            userDatagrid.setUserId(Integer.parseInt(userId));
        }
        Connection con=null;
        try{
            con=dbUtil.getCon();
            int saveNums = 0;
            JSONObject result=new JSONObject();
            //��Ϊ�� ���޸�
            if (StringUtil.isNotEmpty(userId)) {
                saveNums = userDatagridDao.userModify(con, userDatagrid);//�޸ļ�¼
            }else {
                //Ϊ�� �����
                saveNums = userDatagridDao.userAdd(con, userDatagrid);//��Ӽ�¼
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
