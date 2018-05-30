package com.example.duang1996.selectcourseapp.bean;

import android.content.Intent;

import cn.bmob.v3.BmobObject;

/**
 * Created by duang1996 on 2018/5/25.
 */

public class Lesson extends BmobObject{
    private Integer week;          //星期几的课
    private Intent start;          //第几节课开始
    private Integer end;           //第几节课结束
    private Integer startWeek;    //第几周开始上这门课
    private Integer endWeek;       //第几周结束
    private String place;          //上课地点

    public Lesson() {};

    public Lesson(Lesson lesson) {
        this.week = lesson.getWeek();
        this.start = lesson.getStart();
        this.end = lesson.getEnd();
        this.startWeek = lesson.getStartWeek();
        this.endWeek = lesson.getEndWeek();
        this.place = lesson.getPlace();
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getWeek() {
        return week;
    }

    public void setStart(Intent start) {
        this.start = start;
    }

    public Intent getStart() {
        return start;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getEnd() {
        return end;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
