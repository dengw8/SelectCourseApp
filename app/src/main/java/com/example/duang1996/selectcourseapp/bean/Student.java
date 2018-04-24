package com.example.duang1996.selectcourseapp.bean;

import java.io.File;

/**
 * Created by duang1996 on 2018/4/23.
 */

public class Student {
    private String netId;
    private String password;
    private int id;
    private String name;
    private boolean sex;
    private int nation;
    private int college;
    private int major;
    private File avatar;
    private String mobile;
    private int grade;

    public Student() {}

    public void setNetId(String netId) {
        this.netId = netId;
    }
    public String getNetId() {
        return netId;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName() {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setNation(int nation) {
        this.nation = nation;
    }
    public int getNation() {
        return nation;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public boolean getSex() {
        return  sex;
    }
    public void setCollege(int college) {
        this.college = college;
    }
    public int getCollege() {
        return college;
    }
    public void setMajor(int major) {
        this.major = major;
    }
    public int getMajor() {
        return major;
    }
    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }
    public File getAvatar() {
        return avatar;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getGrade() {
        return grade;
    }
}
