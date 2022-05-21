package com.hongbo5.top.dao;
import com.hongbo5.top.model.User;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//管理员账号/密码Dao
public class UserDao {
	/**
	 * 管理员登录验证
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception{
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	public ResultSet userList(Connection con, PageBean pageBean, User user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user");
		//user!=null 查询功能块需要
		if (user!=null && StringUtil.isNotEmpty(user.getUserName())) { sb.append(" and userName like '%" + user.getUserName() + "%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());//分页功能
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		return pstmt.executeQuery();
	}
	public int userCount(Connection con,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_user");
		if (StringUtil.isNotEmpty(user.getUserName())) {
			//and 该条件不一定执行
			sb.append(" and userName like '%" + user.getUserName() + "%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int userDelete(Connection con, String delIds)throws Exception {
		String sql = "delete from t_user where id in(" + delIds + ")";
		PreparedStatement psmt = con.prepareStatement(sql);
		return psmt.executeUpdate();
	}
	public int userAdd(Connection con, User user)throws Exception {
		String sql = "insert into t_user values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassword());
		return pstmt.executeUpdate();
	}
	public int userModify(Connection con,User user)throws Exception {
		String sql = "update t_user set userName=?,password=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassword());
		pstmt.setInt(3,user.getId());
		return pstmt.executeUpdate();
	}
}
