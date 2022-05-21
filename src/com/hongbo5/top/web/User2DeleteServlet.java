package com.hongbo5.top.web;

import com.hongbo5.top.dao.User2Dao;
import com.hongbo5.top.dao.UserDatagridDao;
import com.hongbo5.top.model.User2;
import com.hongbo5.top.util.DbUtil;
import com.hongbo5.top.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "User2DeleteServlet", value = "/user2Delete")
public class User2DeleteServlet extends HttpServlet {
    DbUtil dbUtil = new DbUtil();
    User2Dao user2Dao = new User2Dao();
    UserDatagridDao userDatagridDao = new UserDatagridDao();
    User2 user2 = new User2();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");
        Connection con=null;
        try{
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            //�����Զ���Ϊ���������
            String str[] = delIds.split(",");
            //��Ϊɾ���п����Ƕ������� �򰤸��ж�
            for (int i = 0; i < str.length; i++) {
                boolean f = userDatagridDao.getUserByUser2Id(con, str[i]);
                if (f) {
                    //����û����� �ҵ��������ݲ���ɾ��
                    result.put("errorIndex",i);

                    result.put("errorMsg","�˺������й���Ա��Ϣ������ɾ��!");
                    ResponseUtil.write(response, result);
                    //return�������ֱ�ӽ������������½���
                    return;
                }
            }

            int delNums = user2Dao.user2Delete(con, delIds);//ɾ����¼
            if (delNums > 0) {
                result.put("success", "true");
                result.put("delNums", delNums);
            }else{
                result.put("errorMsg", "ɾ��ʧ��");
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
