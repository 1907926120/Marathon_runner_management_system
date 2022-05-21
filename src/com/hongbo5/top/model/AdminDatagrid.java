package com.hongbo5.top.model;

import java.util.Date;

/**
 * 管理员信息
 */
public class AdminDatagrid {
    //数据库中常用名字  取得独特
    private int adminId;
    private String jobNo;
    private String name;
    private String sex;
    private Date birthday;
    private int userId=-1;
    private String email;
    private String synopses;
//企业开发  能不关联就不关联 节约成本
   private String userName;
   private String password;

    public AdminDatagrid() {
        super();
    }

    public AdminDatagrid(String jobNo, String name, String sex, Date birthday, int userId, String email, String synopses) {
        super();
        this.jobNo = jobNo;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.userId = userId;
        this.email = email;
        this.synopses = synopses;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSynopses() {
        return synopses;
    }

    public void setSynopses(String synopses) {
        this.synopses = synopses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
