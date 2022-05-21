package com.hongbo5.top.dao;

import com.hongbo5.top.model.MarathonerDatagrid;
import com.hongbo5.top.model.PageBean;
import com.hongbo5.top.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * �˶�Ա��ϢDao
 */
public class MarathonerDatagridDao {
    //���ӻ�֮����
    public ResultSet charts1(Connection con) throws Exception {
        StringBuffer sb = new StringBuffer("SELECT nationality, COUNT(1) AS count  FROM t_marathonerInfo  WHERE id<200 GROUP BY nationality ORDER BY nationality ASC LIMIT 5");
        PreparedStatement pstmt = con.prepareStatement(String.valueOf(sb));
        return pstmt.executeQuery();
    }

    //�������˼��
    //����ѯ����
    public ResultSet marathonerList(Connection con, PageBean pageBean, MarathonerDatagrid marathonerDatagrid)throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_marathonerInfo");
        //Ч����ߵ��㷨 ����Ŀ һ�����ϵ�����  ��ѯ����
        if (StringUtil.isNotEmpty(marathonerDatagrid.getName())) {
            //and ��������һ��ִ��
            sb.append(" and name like '%" + marathonerDatagrid.getName() + "%'");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());//��ҳ����
        }
        //�����2.��ѯ���� ����replaceFirst�������� ����and�����滻Ϊwhere
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }
    //��ȡȫ������
    /**
     * ��ѯ֮���ǵ���ѯ��ҳ����������ʾbug���
     * ������� INSERT INTO t_marathonerinfo VALUES(NULL,'XXX','X','XX','XXXX','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX');
     * @param con
     * @return
     * @throws Exception
     */
    public int marathonerCount(Connection con,MarathonerDatagrid marathonerDatagrid)throws Exception{
        StringBuffer sb=new StringBuffer("select count(*) as total from t_marathonerInfo");
        if (StringUtil.isNotEmpty(marathonerDatagrid.getName())) {
            //and ��������һ��ִ��
            sb.append(" and name like '%" + marathonerDatagrid.getName() + "%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }
    /**
     * delete from tableName where field in(1,2,3)  sqlɾ����䣬����forѭ����Ч�ʸ�
     * @param con
     * @param delIds
     * @return
     */
    public int marathonerDelete(Connection con, String delIds)throws Exception {
        String sql = "delete from t_marathonerInfo where id in(" + delIds + ")";
        PreparedStatement psmt = con.prepareStatement(sql);
        return psmt.executeUpdate();
    }

    /**
     * ��ӹ���
     * @param con
     * @param marathonerDatagrid
     * @return
     * @throws Exception
     */
    public int marathonerAdd(Connection con, MarathonerDatagrid marathonerDatagrid)throws Exception {
        String sql = "insert into t_marathonerInfo values(null,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,marathonerDatagrid.getName());
        pstmt.setString(2,marathonerDatagrid.getSex());
        pstmt.setString(3,marathonerDatagrid.getForeignName());
        pstmt.setString(4,marathonerDatagrid.getNationality());
        pstmt.setString(5,marathonerDatagrid.getBirthday());
        pstmt.setInt(6,marathonerDatagrid.getStature());
        pstmt.setInt(7,marathonerDatagrid.getWeight());
        pstmt.setString(8,marathonerDatagrid.getAwards());
        return pstmt.executeUpdate();
    }
    /**
     * ���¹���
     * @param con
     * @param marathonerDatagrid
     * @return
     * @throws Exception
     */
    public int marathonerModify(Connection con,MarathonerDatagrid marathonerDatagrid)throws Exception {
        String sql = "update t_marathonerInfo set name=?,sex=?,foreignName=?,nationality=?,birthday=?,stature=?,weight=?,awards=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,marathonerDatagrid.getName());
        pstmt.setString(2,marathonerDatagrid.getSex());
        pstmt.setString(3,marathonerDatagrid.getForeignName());
        pstmt.setString(4,marathonerDatagrid.getNationality());
        pstmt.setString(5,marathonerDatagrid.getBirthday());
        pstmt.setInt(6,marathonerDatagrid.getStature());
        pstmt.setInt(7,marathonerDatagrid.getWeight());
        pstmt.setString(8,marathonerDatagrid.getAwards());
        pstmt.setInt(9,marathonerDatagrid.getId());
        return pstmt.executeUpdate();
    }
}
