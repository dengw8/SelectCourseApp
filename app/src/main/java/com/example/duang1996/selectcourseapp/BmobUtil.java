package com.example.duang1996.selectcourseapp;

import android.content.Context;
import android.util.Log;

import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;
import com.example.duang1996.selectcourseapp.bean.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by duang1996 on 2018/5/24.
 *
 * Bmob的封装类
 * 负责维护服务端
 * 同时提供一些接口供前端调用
 */

public class BmobUtil {
    private String appId = "42f7621fe647eab4d96f965777dd8db9";

    private static BmobUtil instance;

    private BmobUtil() {}

    public static BmobUtil getInstance() {
        if(instance == null)  {
            instance = new BmobUtil();
        }
        return instance;
    }

     public void initializeApp(Context context) {
        Bmob.initialize(context, appId);
     }

    /*
     * 查询当前学生的可选课程列表
     * @param 当前学生实例的引用
     */
    public List<Course> getSelectableCourseList(int major) {
        final List<Course> result = new ArrayList<>();

        // 构造一个CountDownLatch实例
        final CountDownLatch c = new CountDownLatch(1);

        BmobQuery<Course> query = new BmobQuery<Course>();
        // 基于专业查询（待完善）
        query.addWhereEqualTo("major", major);
        // 执行查询方法（注意 FindListener是一个异步方法）
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> objects, BmobException e) {
                if (e == null) {
                    result.addAll(objects);
                    c.countDown();
                } else {
                    Log.i("mydebug", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
        // 阻塞当前线程，直到异步方法执行完调用了countDown()方法
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 获取当前user的已选课程的ObjectId数组
     * @param 当前学生的ObjectId
     */
    public List<String> getSelectedCourseObjectIdList(String userObjectId) {
        final List<String> result = new ArrayList<>();

        // 构造一个CountDownLatch实例
        final CountDownLatch c = new CountDownLatch(1);

        // 根据ObjectId 获取到该Student的对象
        BmobQuery<Student> query = new BmobQuery<Student>();
        query.getObject(userObjectId, new QueryListener<Student>() {
            @Override
            public void done(Student object, BmobException e) {
                if(object != null){
                    result.addAll(object.getSelectedCourse());
                    c.countDown();
                }else{
                    Log.i("bmob","失败：" + e.getMessage()+","+e.getErrorCode());
                }
            }
        });
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 获取当前user的待筛选课程的ObjectId 数组
     * @param 当前学生的ObjectId
     */
    public List<String> getSelectingCourseObjectIdList(String userObjectId) {
        final List<String> result = new ArrayList<>();

        // 构造一个CountDownLatch实例
        final CountDownLatch c = new CountDownLatch(1);

        // 根据ObjectId 获取到该Student的对象
        BmobQuery<Student> query = new BmobQuery<Student>();
        query.getObject(userObjectId, new QueryListener<Student>() {
            @Override
            public void done(Student object, BmobException e) {
                if(object != null){
                    result.addAll(object.getSelectingCourse());
                    c.countDown();
                }else{
                    Log.i("bmob","失败：" + e.getMessage()+","+e.getErrorCode());
                }
            }
        });
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 获取课程的ObjectId 对应的 Course对象
     * @param Course的ObjectId
     * 返回值只有一个Course,但是单个final修饰的Course不能进行赋值了
     * 所以使用数组类型的返回值，但其实返回值只有一个
     */
    public Course fromObjectIdToCourse(String objectId) {
        final List<Course> result = new ArrayList<>();

        // 构造一个CountDownLatch实例
        final CountDownLatch c = new CountDownLatch(1);

        BmobQuery<Course> query = new BmobQuery<Course>();
        query.getObject(objectId, new QueryListener<Course>() {
            @Override
            public void done(Course object, BmobException e) {
                if(object != null){
                    result.add(object);
                    c.countDown();
                }else{
                    Log.i("bmob","失败：" + e.getMessage()+","+e.getErrorCode());
                }
            }
        });
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.get(0);
    }

    /*
     * 通过的Lesson的ObjectId获取Lesson对象
     * @param Lesson的ObjectId
     */
    public Lesson fromObjectIdToLesson(String objectId) {
        final List<Lesson> result = new ArrayList<>();
        // 构造一个CountDownLatch实例
        final CountDownLatch c = new CountDownLatch(1);

        BmobQuery<Lesson> query = new BmobQuery<Lesson>();
        query.getObject(objectId, new QueryListener<Lesson>() {
            @Override
            public void done(Lesson object, BmobException e) {
                if(object != null){
                    result.add(object);
                    c.countDown();
                }else{
                    Log.i("bmob","失败：" + e.getMessage()+","+e.getErrorCode());
                }
            }
        });
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.get(0);
    }

    /*
     * 选择一门课程的后台逻辑
     * 具体的逻辑为将该课程的ObjectId 添加到student的待筛选课程列表当中
     * @param1 Course的ObjectId
     * @param2 User对象
     * @param3 Course的当前cover数
     */
    public void selectOneCourse(final String objectId, Student user, final int cover) {
        user.addUnique("selectingCourse", objectId);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null) {
                    // 更新course的cover字段
                    updateCourseCover(objectId, cover, true);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     * 删除一门待筛选中的课程
     * 后台逻辑为将该Course从待筛选课程列表中移除
     * @param1 Course的ObjectId
     * @param2 user的ObjectId
     */
    public void removeCourseFromSelectingCourseList(final String courseObjectId, Student user, final int cover) {
        user.removeAll("selectingCourse", Collections.singletonList(courseObjectId));
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e != null) {
                    e.printStackTrace();
                } else {
                    updateCourseCover(courseObjectId, cover, false);
                }
            }
        });
    }

    /*
     * 删除一门选课成功的课程
     * 后台逻辑为将该Course从已选课程列表中移除
     * @param Course的ObjectId
     * @param2 user的ObjectId
     */
    public void removeCourseFromSelectedCourseList(final String courseObjectId, Student user, final int cover) {
        user.removeAll("selectedCourse", Collections.singletonList(courseObjectId));
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e != null) {
                    e.printStackTrace();
                } else {
                    updateCourseCover(courseObjectId, cover, false);
                }
            }
        });
    }

    /*
     * 对课程cover字段的更新函数
     * @param Course的ObjectId
     * @param2 Course的当前cover数
     * @param3 标记位，区分增加还是减少cover
     */
    private void updateCourseCover(String objectId, int cover, boolean sign) {
        if(sign) {
            cover++;
        } else {
            cover--;
        }
        Course course = new Course();
        course.setCover(cover);
        course.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e != null) {
                    e.printStackTrace();
                }
            }
        });
    }
}
