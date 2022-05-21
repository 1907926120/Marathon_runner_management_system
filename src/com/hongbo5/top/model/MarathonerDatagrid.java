package com.hongbo5.top.model;

/**
 * 运动员信息
 */
public class MarathonerDatagrid {
    private int id;
    private String name;
    private String sex;
    private String foreignName;
    private String nationality;
    private String birthday;
    private int stature;
    private int weight;
    private String awards;

    public MarathonerDatagrid() {
        super();
    }

    public MarathonerDatagrid(String name, String sex, String foreignName, String nationality, String birthday, int stature, int weight, String awards) {
        super();
        this.name = name;
        this.sex = sex;
        this.foreignName = foreignName;
        this.nationality = nationality;
        this.birthday = birthday;
        this.stature = stature;
        this.weight = weight;
        this.awards = awards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }
}
