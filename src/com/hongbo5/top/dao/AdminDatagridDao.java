package com.hongbo5.top.dao;

import com.hongbo5.top.model.AdminDatagrid;
import com.hongbo5.top.model.AdminDatagrid;
import com.hongbo5.top.model.MarathonerDatagrid;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.DateUtil;
import com.hongbo5.top.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 管理员信息Dao
 */
public class AdminDatagridDao {
    public ResultSet adminList(Connection con, PageBean pageBean,AdminDatagrid adminDatagrid,String bBirthday,String eBirthday)throws Exception{
        //关联查询
        //select * :"*"不明确，数据库混乱，导致获得id不理想
        //数据库取名字独特 加前缀
        StringBuffer sb=new StringBuffer("select * from t_adminInfo a,t_user u1 where a.userId=u1.id");
        //like 模糊查询
        if (StringUtil.isNotEmpty(adminDatagrid.getJobNo())) {
            sb.append(" and a.jobNo like '%" + adminDatagrid.getJobNo() + "%'");
        } if (StringUtil.isNotEmpty(adminDatagrid.getName())) {
            sb.append(" and a.name like '%" + adminDatagrid.getName() + "%'");
        }
        //精确查询
        if (StringUtil.isNotEmpty(adminDatagrid.getSex())) {
            sb.append(" and a.sex ='"+adminDatagrid.getSex()+"'");
        }
        //userId数据操作  在model中定义userId值为-1
        if (adminDatagrid.getUserId()!=-1) {
            sb.append(" and a.userId ='"+adminDatagrid.getUserId()+"'");
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
    public int adminCount(Connection con,AdminDatagrid adminDatagrid,String bBirthday,String eBirthday)throws Exception{
        //关联查询
        StringBuffer sb=new StringBuffer("select count(*) as total from t_adminInfo a,t_user u1 where a.userId=u1.id");
        if (StringUtil.isNotEmpty(adminDatagrid.getJobNo())) {
            sb.append(" and a.jobNo like '%" + adminDatagrid.getJobNo() + "%'");
        } if (StringUtil.isNotEmpty(adminDatagrid.getName())) {
            sb.append(" and a.name like '%" + adminDatagrid.getName() + "%'");
        }
        //精确查询
        if (StringUtil.isNotEmpty(adminDatagrid.getSex())) {
            sb.append(" and a.sex ='"+adminDatagrid.getSex()+"'");
        }
        //userId数据操作  在model中定义userId值为-1
        if (adminDatagrid.getUserId()!=-1) {
            sb.append(" and a.userId ='"+adminDatagrid.getUserId()+"'");
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
    public int adminDelete(Connection con, String delIds)throws Exception {
        String sql = "delete from t_adminInfo where adminId in(" + delIds + ")";
        PreparedStatement psmt = con.prepareStatement(sql);
        return psmt.executeUpdate();
    }
    public int adminAdd(Connection con, AdminDatagrid adminDatagrid)throws Exception {
        String sql = "insert into t_adminInfo values(null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,adminDatagrid.getJobNo());
        pstmt.setString(2,adminDatagrid.getName());
        pstmt.setString(3,adminDatagrid.getSex());
        pstmt.setString(4, DateUtil.formatDate(adminDatagrid.getBirthday(),"yyyy-MM-dd"));
        pstmt.setInt(5,adminDatagrid.getUserId());
        pstmt.setString(6,adminDatagrid.getEmail());
        pstmt.setString(7,adminDatagrid.getSynopses());
        return pstmt.executeUpdate();
    }
    public int adminModify(Connection con, AdminDatagrid adminDatagrid)throws Exception {
        String sql = "update t_adminInfo set jobNo=?,name=?,sex=?,birthday=?,userId=?,email=?,synopses=? where adminId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,adminDatagrid.getJobNo());
        pstmt.setString(2,adminDatagrid.getName());
        pstmt.setString(3,adminDatagrid.getSex());
        pstmt.setString(4, DateUtil.formatDate(adminDatagrid.getBirthday(),"yyyy-MM-dd"));
        pstmt.setInt(5,adminDatagrid.getUserId());
        pstmt.setString(6,adminDatagrid.getEmail());
        pstmt.setString(7,adminDatagrid.getSynopses());
        pstmt.setInt(8,adminDatagrid.getAdminId());
        return pstmt.executeUpdate();
    }
//判断是否有数据
    public boolean getAdminByUserId(Connection con, String userId) throws Exception {
        String sql = "select * from t_adminInfo where userId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,userId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        }else{
            return false;
        }
    }
}
