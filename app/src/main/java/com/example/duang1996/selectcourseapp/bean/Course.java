package com.example.duang1996.selectcourseapp.bean;

/**
 * Created by duang1996 on 2018/4/23.
 */

public class Course {
    private String id;           // 课程id（课程号）
    private String name;        // 课程名称
    private int credit;        // 课程学分
    private int type;          // 课程类别（1.公必 2.公选 3.专必 4.专选 5.限选）
    private int teacherId;     // 老师Id
    private int capacity;      // 课容量
    private int cover;         // 课程已选人数
    private int year;           // 学年度（计开始的年份， 如：2017代表2017-2018学年）
    private int term;           // 第一/二学期
    private int startWeek;      //  课程开始的周数
    private int endWeek;        //  课程结束的周数
    private int exam;           //  考核方式（1.开卷 2.闭卷 3.考查）
    private int college;        // 开课学院 （待规定）
    private int campus;         // 上课校区(1.南校 2.东校 3.北校 4.珠海校区 5.深圳校区)
    private String address;     // 具体上课地点（如：B205）
    private String require;     // 选课要求
    private int day1 = 0;       // 每周第一次上课的星期数（1-7）
    private int start1;         // 第一次课开始的节数（1-10）
    private int end1;           // 第一节课结束的节数（2-11）
    private int day2 = 0;        // 每周第二次上课的星期数（1-7），为0 的时候表示该周不上这节课
    private int start2;         // 第二次课开始的节数（1-10）
    private int end2;           // 第二节课结束的节数（2-11）

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
    public int getCredit() {
        return credit;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId =teacherId;
    }
    public int getTeacherId() {
        return teacherId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCover(int cover) {
        this.cover = cover;
    }
    public int getCover() {
        return cover;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }
    public void setTerm(int term) {
        this.term = term;
    }
    public int getTerm() {
        return term;
    }
    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }
    public int getStartWeek() {
        return startWeek;
    }
    public void setEndWeek(int end) {
        this.endWeek = end;
    }
    public int getEndWeek() {
        return endWeek;
    }
    public void setExam(int exam) {
        this.exam = exam;
    }
    public int getExam() {
        return exam;
    }
    public void setCollege(int college) {
        this.college = college;
    }
    public int getCollege() {
        return college;
    }
    public void setCampus(int campus) {
        this.campus = campus;
    }
    public int getCampus() {
        return campus;
    }
    public void setAddress(String addr) {
        address = addr;
    }
    public String getAddress() {
        return address;
    }
    public void setRequire(String require) {
        this.require = require;
    }
    public String getRequire() {
        return require;
    }
    public void setDay1(int day1) {
        this.day1 = day1;
    }
    public int getDay1() {
        return day1;
    }
    public void setStart1(int start1) {
        this.start1 = start1;
    }
    public int getStart1() {
        return start1;
    }
    public void setEnd1(int end1) {
        this.end1 = end1;
    }
    public int getEnd1() {
        return  end1;
    }
    public void setDay2(int day2) {
        this.day2 = day2;
    }
    public int getDay2() {
        return day2;
    }
    public void setStart2(int start2) {
        this.start2 = start2;
    }
    public int getStart2() {
        return start2;
    }
    public void setEnd2(int end2) {
        this.end2 = end2;
    }
    public int getEnd2() {
        return end2;
    }
}

