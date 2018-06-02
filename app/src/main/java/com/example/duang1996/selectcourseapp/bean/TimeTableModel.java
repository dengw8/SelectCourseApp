package com.example.duang1996.selectcourseapp.bean;

/**
 * Created by duang1996 on 2018/4/24.
 */

public class TimeTableModel {
    private String name;             // 课程名字
    private int startWeek;          // 课程开始的周数
    private int endWeek;            // 课程结束的周数
    private int startNum;           // 课程开始的节数
    private int endNum;             // 课程结束的节数
    private int week;               // 星期几
    private String place;           // 上课地点

    public TimeTableModel() {
        // TODO Auto-generated constructor stub
    }

    public TimeTableModel(String name, int startWeek, int endWeek, int startNum, int endNum, int week, String place) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startNum = startNum;
        this.endNum = endNum;
        this.week = week;
        this.place = place;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStartWeek() {
        return startWeek;
    }
    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int  getEndWeek() {
        return endWeek;
    }
    public void setEndWeek(int  endWeek) {
        this.endNum = endNum;
    }

    public int getStartNum() {
        return startNum;
    }
    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }
    public void setEndNum(int endnum) {
        this.endNum = endnum;
    }

    public int getWeek() {
        return week;
    }
    public void setWeek(int week) {
        this.week = week;
    }

    public String getPlace()
    {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return name + "@" + place;
    }
}