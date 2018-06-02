package com.example.duang1996.selectcourseapp.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by duang1996 on 2018/4/23.
 */

public class Course extends BmobObject implements Serializable{
    private String id;              // 课程id（课程号）
    private String name;            // 课程名称
    private Integer credit;         // 课程学分
    private Integer type;           // 课程类别（1.公必 2.公选 3.专必 4.专选 5.限选）
    private String teacherName;     // 老师Id
    private Integer capacity;       // 课容量
    private Integer cover;          // 课程已选人数
    private Integer screen;         // 课程待筛选人数
    private Integer screenWay;     //  课程筛选方式
    private Integer year;           // 学年度（计开始的年份， 如：2017代表2017-2018学年）
    private Integer term;           // 第一/二学期(1. 第一学期 2.第二学期)
    private Integer exam;           // 考核方式（1.开卷 2.闭卷 3.考查）
    private Integer college;        // 开课学院 （待规定）
    private Integer campus;         // 上课校区(1.南校 2.东校 3.北校 4.珠海校区 5.深圳校区)
    private String require;         // 选课要求
    private Lesson lesson1;         // 第一次课
    private Lesson lesson2;         // 第二次课
    private Integer major;          //可选择这门课的专业，和Student类的major变量匹配，0 表示所有专业可选

    public Course() {}

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
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public Integer getCredit() {
        return credit;
    }
    public void setType(int type) {
        this.type = type;
    }
    public Integer getType() {
        return type;
    }
    public void setTeacherName(String teacherId) {
        this.teacherName =teacherId;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCover(int cover) {
        this.cover = cover;
    }
    public Integer getCover() {
        return cover;
    }
    public void setScreen(int screen) {
        this.screen = screen;
    }
    public Integer getScreen() {
        return screen;
    }
    public void setScreenWay(int screenWay) {
        this.screenWay = screenWay;
    }
    public Integer getScreenWay() {
        return screenWay;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public Integer getYear() {
        return year;
    }
    public void setTerm(int term) {
        this.term = term;
    }
    public Integer getTerm() {
        return term;
    }
    public void setExam(int exam) {
        this.exam = exam;
    }
    public Integer getExam() {
        return exam;
    }
    public void setCollege(int college) {
        this.college = college;
    }
    public Integer getCollege() {
        return college;
    }
    public void setCampus(int campus) {
        this.campus = campus;
    }
    public Integer getCampus() {
        return campus;
    }
    public void setRequire(String require) {
        this.require = require;
    }
    public String getRequire() {
        return require;
    }

    public void setLesson1(Lesson lesson1) {
        this.lesson1 = new Lesson(lesson1);
    }

    public Lesson getLesson1() {
        return lesson1;
    }

    public void setLesson2(Lesson lesson2) {
        this.lesson2 = new Lesson(lesson2);
    }

    public Lesson getLesson2() {
        return lesson2;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getMajor() {
        return major;
    }
}

