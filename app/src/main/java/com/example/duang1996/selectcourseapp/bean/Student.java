package com.example.duang1996.selectcourseapp.bean;

import java.io.File;

/**
 * Created by duang1996 on 2018/4/23.
 */

public class Student {
    private String netId;          // NetId
    private String password;       // 登录密码
    private String id;             // 学号
    private String name;           // 姓名
    private boolean sex;          // 性别
    private int college;          // 学院
    private int major;            // 专业
    private String mobile;        // 手机号
    private int grade;            // 入学年份（根据年份确定大几）

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
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName() {
        this.name = name;
    }
    public String getName() {
        return name;
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
