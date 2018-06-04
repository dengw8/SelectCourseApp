package com.example.duang1996.selectcourseapp.global;

import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duang1996 on 2018/5/27.
 *
 *  使用两个全局变量分别用来保存 可选课程和 已选课程
 *  这里使用全局变量的考虑是因为在多个Activity和Fragment中都会使用到这两个变量
 *  直接声明为全局的方式可以减小开销
 *  还有一点考虑就是这两个变量都是通过从后台数据库中获取的,这样可以减少多次加载的开销
 */

public class Global {
    /*
     * 这两个变量初次在MainActivity中从后台数据库读取
     */

    // 可选择课程列表
    private static List<Course> selectableCourseList = new ArrayList<>();
    // 已选课程列表
    private static List<Course> selectedCourseList = new ArrayList<>();
    // 待筛选课程列表
    private static List<Course> selectingCourseList = new ArrayList<>();
    // Lesson的列表（这儿设计的不太好）
    private static List<Lesson> lessonList = new ArrayList<>();
    // 课程表界面中Spinner选择的pos
    public static int pos = -1;

    /*
     * 提供一些静态方法操作这些List
     */

    // 添加一个item
    public static void addSelectableCourseList(Course course) {
        selectableCourseList.add(course);
    }
    public static void addSelectedCourseList(Course course) {
        selectedCourseList.add(course);
    }
    public static void addSelectingCourseList(Course course) {
        selectingCourseList.add(course);
    }
    public static void addLessonList(Lesson lesson) {
        lessonList.add(lesson);
    }

    // 添加一个list
    public static void addAllSelectableCourseList(List<Course> list) {
        selectableCourseList.addAll(list);
    }
    public static void addAllSelectedCourseList(List<Course> list) {
        selectedCourseList.addAll(list);
    }
    public static void addAllSelectingCourseList(List<Course> list) {
        selectingCourseList.addAll(list);
    }
    public static void addAllLessonList(List<Lesson> list) {
        lessonList.addAll(list);
    }

    // 删除一个item,参数为Index
    public static void removeSelectableCourseList(int i) {
        selectableCourseList.remove(i);
    }
    public static void removeSelectedCourseList(int i) {
        selectedCourseList.remove(i);
    }
    public static void removeSelectingCourseList(int i) {
        selectingCourseList.remove(i);
    }
    public static void removeLessonList(int i) {
        lessonList.remove(i);
    }

    // 删除一个item,参数为对象实例
    public static void removeSelectableCourseList(Course item) {
        selectableCourseList.remove(item);
    }
    public static void removeSelectedCourseList(Course item) {
        selectedCourseList.remove(item);
    }
    public static void removeSelectingCourseList(Course item) {
        selectingCourseList.remove(item);
    }
    public static void removeLessonList(Lesson lesson) {
        lessonList.remove(lesson);
    }

    // 清空整个列表
    public static void clearSelectableCourseList() {
        selectableCourseList.clear();
    }
    public static void clearSelectedCourseList() {
        selectedCourseList.clear();
    }
    public static void clearSelectingCourseList() {
        selectingCourseList.clear();
    }
    public static void clearLessonList() {
        lessonList.clear();
    }

    // 根据下标获取一个item
    public static Course getSelectableCourse(int i) {
        return selectableCourseList.get(i);
    }
    public static Course getSelectedCourse(int i) {
        return selectedCourseList.get(i);
    }
    public static Course getSelectingCourse(int i) {
        return selectingCourseList.get(i);
    }
    public static Lesson getLessonList(int i) {
        return lessonList.get(i);
    }

    // 返回整个List
    public static List<Course> getSelectableCourseList(){
        return selectableCourseList;
    }
    public static List<Course> getSelectedCourseList(){
        return selectedCourseList;
    }
    public static List<Course> getSelectingCourseList(){
        return selectingCourseList;
    }
    public static List<Lesson> getLessonList() {
        return lessonList;
    }

    // 返回List的长度
    public static int getSelectableCourseListSize(){
        return selectableCourseList.size();
    }
    public static int getSelectedCourseListSize(){
        return selectedCourseList.size();
    }
    public static int getSelectingCourseListSize(){
        return selectingCourseList.size();
    }
    public static int getLessonListSize() {
        return lessonList.size();
    }
}
