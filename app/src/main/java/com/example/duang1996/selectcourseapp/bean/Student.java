package com.example.duang1996.selectcourseapp.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by duang1996 on 2018/4/23.
 */

public class Student extends BmobUser {

    /* BmobUser已经包括的属性有
     * username: 用户的用户名（登录名，这里指代NetId）
     * password: 用户的密码(登录密码)
     * mobilePhoneNumber (手机号码)
     */
    private String id;                  // 学号
    private String name;                // 姓名
    private Boolean sex;                // 性别
    private Integer college;            // 学院
    private Integer major;              // 专业
    private Integer grade;              // 入学年份（根据年份确定大几）
    private List<String> selectedCourse;     // 已选课程列表,记录课程Id
    private List<String> selectingCourse;   // 待筛选课程列表，纪录课程Id

    public Student() {}

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public Boolean getSex() {
        return  sex;
    }
    public void setCollege(int college) {
        this.college = college;
    }
    public Integer getCollege() {
        return college;
    }
    public void setMajor(int major) {
        this.major = major;
    }
    public Integer getMajor() {
        return major;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public Integer getGrade() {
        return grade;
    }
    public void setSelectedCourse(List<String> list) {
        selectedCourse = new ArrayList<String>(list);
    }
    public List<String> getSelectedCourse() {
        return selectedCourse;
    }
    public void setSelectingCourse(List<String> list) {
        selectingCourse = new ArrayList<String>(list);
    }
    public List<String> getSelectingCourse() {
        return selectingCourse;
    }
}