package com.hongbo5.top.model;

import java.util.Date;

/**
 * 用户信息
 */
public class UserDatagrid {

    private int userId;
    private String jobNo;
    private String name;
    private String sex;
    private Date birthday;
    private int user2Id=-1;
    private String email;
    private String synopses;

    private String userName;
    private String password;

    public UserDatagrid() {
        super();
    }

    public UserDatagrid(String jobNo, String name, String sex, Date birthday, int user2Id, String email, String synopses) {
        super();
        this.jobNo = jobNo;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.user2Id = user2Id;
        this.email = email;
        this.synopses = synopses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
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
