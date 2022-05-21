package com.hongbo5.top.model;

/**
 * 用户
 * @author www.java1234.com
 *
 */
public class User2 {

    private int id;
    private String userName;
    private String password;

    public User2() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User2(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
