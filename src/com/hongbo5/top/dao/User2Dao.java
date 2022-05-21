package com.hongbo5.top.dao;

import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.model.User2;
import com.hongbo5.top.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//管理员账号/密码Dao
public class User2Dao {
	/**
	 * 管理员登录验证
	 * @param con
	 * @param user2
	 * @return
	 * @throws Exception
	 */
	public User2 login(Connection con, User2 user2) throws Exception{
		User2 resultUser=null;
		String sql="select * from t_user2 where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user2.getUserName());
		pstmt.setString(2, user2.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User2();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	public ResultSet user2List(Connection con, PageBean pageBean, User2 user2)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user2");

		if (user2!=null && StringUtil.isNotEmpty(user2.getUserName())) { sb.append(" and userName like '%" + user2.getUserName() + "%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());//分页功能
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		return pstmt.executeQuery();
	}
	public int user2Count(Connection con,User2 user2)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_user2");
		if (StringUtil.isNotEmpty(user2.getUserName())) {
			//and 该条件不一定执行
			sb.append(" and userName like '%" + user2.getUserName() + "%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int user2Delete(Connection con, String delIds)throws Exception {
		String sql = "delete from t_user2 where id in(" + delIds + ")";
		PreparedStatement psmt = con.prepareStatement(sql);
		return psmt.executeUpdate();
	}
	public int user2Add(Connection con, User2 user2)throws Exception {
		String sql = "insert into t_user2 values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,user2.getUserName());
		pstmt.setString(2,user2.getPassword());
		return pstmt.executeUpdate();
	}
	public int user2Modify(Connection con,User2 user2)throws Exception {
		String sql = "update t_user2 set userName=?,password=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,user2.getUserName());
		pstmt.setString(2,user2.getPassword());
		pstmt.setInt(3,user2.getId());
		return pstmt.executeUpdate();
	}
}
