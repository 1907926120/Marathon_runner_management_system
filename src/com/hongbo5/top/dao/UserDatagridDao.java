package com.hongbo5.top.dao;

import com.hongbo5.top.model.UserDatagrid;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.DateUtil;
import com.hongbo5.top.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 用户信息Dao
 */
public class UserDatagridDao {
    public ResultSet userList(Connection con, PageBean pageBean,UserDatagrid userDatagrid,String bBirthday,String eBirthday)throws Exception{
        //关联查询
        //select * :"*"不明确，数据库混乱，导致获得id不理想
        //数据库取名字独特 加前缀
        StringBuffer sb=new StringBuffer("select * from t_userInfo a,t_user2 u2 where a.user2Id=u2.id");
        //like 模糊查询
        if (StringUtil.isNotEmpty(userDatagrid.getJobNo())) {
            sb.append(" and a.jobNo like '%" + userDatagrid.getJobNo() + "%'");
        } if (StringUtil.isNotEmpty(userDatagrid.getName())) {
            sb.append(" and a.name like '%" + userDatagrid.getName() + "%'");
        }
        //精确查询
        if (StringUtil.isNotEmpty(userDatagrid.getSex())) {
            sb.append(" and a.sex ='"+userDatagrid.getSex()+"'");
        }
        //user2Id数据操作  在model中定义user2Id值为-1
        if (userDatagrid.getUser2Id()!=-1) {
            sb.append(" and a.user2Id ='"+userDatagrid.getUser2Id()+"'");
        }
        //bBirthday--eBirthday范围   数据库操作
        //mysql中 TO_DAYS()
        if (StringUtil.isNotEmpty(bBirthday)) {
            sb.append(" and TO_DAYS(a.birthday)>=TO_DAYS('"+bBirthday+"')");
        } if (StringUtil.isNotEmpty(eBirthday)) {
            sb.append(" and TO_DAYS(a.birthday)<=TO_DAYS('"+eBirthday+"')");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());//分页功能
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }
    public int userCount(Connection con,UserDatagrid userDatagrid,String bBirthday,String eBirthday)throws Exception{
        //关联查询
        StringBuffer sb=new StringBuffer("select count(*) as total from t_userInfo a,t_user2 u2 where a.user2Id=u2.id");
        if (StringUtil.isNotEmpty(userDatagrid.getJobNo())) {
            sb.append(" and a.jobNo like '%" + userDatagrid.getJobNo() + "%'");
        } if (StringUtil.isNotEmpty(userDatagrid.getName())) {
            sb.append(" and a.name like '%" + userDatagrid.getName() + "%'");
        }
        //精确查询
        if (StringUtil.isNotEmpty(userDatagrid.getSex())) {
            sb.append(" and a.sex ='"+userDatagrid.getSex()+"'");
        }
        //user2Id数据操作  在model中定义user2Id值为-1
        if (userDatagrid.getUser2Id()!=-1) {
            sb.append(" and a.user2Id ='"+userDatagrid.getUser2Id()+"'");
        }
        //bBirthday--eBirthday范围   数据库操作
        //mysql中 TO_DAYS()
        if (StringUtil.isNotEmpty(bBirthday)) {
            sb.append(" and TO_DAYS(a.birthday)>=TO_DAYS('"+bBirthday+"')");
        } if (StringUtil.isNotEmpty(eBirthday)) {
            sb.append(" and TO_DAYS(a.birthday)<=TO_DAYS('"+eBirthday+"')");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }
    public int userDelete(Connection con, String delIds)throws Exception {
        String sql = "delete from t_userInfo where userId in(" + delIds + ")";
        PreparedStatement psmt = con.prepareStatement(sql);
        return psmt.executeUpdate();
    }
    public int userAdd(Connection con, UserDatagrid userDatagrid)throws Exception {
        String sql = "insert into t_userInfo values(null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,userDatagrid.getJobNo());
        pstmt.setString(2,userDatagrid.getName());
        pstmt.setString(3,userDatagrid.getSex());
        pstmt.setString(4, DateUtil.formatDate(userDatagrid.getBirthday(),"yyyy-MM-dd"));
        pstmt.setInt(5,userDatagrid.getUser2Id());
        pstmt.setString(6,userDatagrid.getEmail());
        pstmt.setString(7,userDatagrid.getSynopses());
        return pstmt.executeUpdate();
    }
    public int userModify(Connection con, UserDatagrid userDatagrid)throws Exception {
        String sql = "update t_userInfo set jobNo=?,name=?,sex=?,birthday=?,user2Id=?,email=?,synopses=? where userId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,userDatagrid.getJobNo());
        pstmt.setString(2,userDatagrid.getName());
        pstmt.setString(3,userDatagrid.getSex());
        pstmt.setString(4, DateUtil.formatDate(userDatagrid.getBirthday(),"yyyy-MM-dd"));
        pstmt.setInt(5,userDatagrid.getUser2Id());
        pstmt.setString(6,userDatagrid.getEmail());
        pstmt.setString(7,userDatagrid.getSynopses());
        pstmt.setInt(8,userDatagrid.getUserId());
        return pstmt.executeUpdate();
    }
//判断是否有数据
    public boolean getUserByUser2Id(Connection con, String user2Id) throws Exception {
        String sql = "select * from t_userInfo where user2Id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,user2Id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        }else{
            return false;
        }
    }
}
