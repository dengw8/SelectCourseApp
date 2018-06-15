package com.example.duang1996.selectcourseapp.util;

import android.text.TextUtils;

import com.example.duang1996.selectcourseapp.bean.Student;
import com.example.duang1996.selectcourseapp.global.Global;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by duang1996 on 2018/5/23.
 *
 * BmobUser的封装类
 */

public class BmobUserUtil {

    /*
     * 单例模式获取bmobUser操作相关的实例
     * 由于bmobUser没涉及到多线程操作
     * 因此'懒汉'式的单例模式满足
     */
    private static BmobUserUtil instance;

    private BmobUserUtil() {}
    public static BmobUserUtil getInstance() {
        if(instance == null) {
            instance = new BmobUserUtil();
        }
        return instance;
    }

    public Student getCurrentUser() {
        return BmobUser.getCurrentUser(Student.class);
    }

    public void login(String username, String password, final LogInListener listener) {
        if (TextUtils.isEmpty(username.trim()) || TextUtils.isEmpty(password.trim())) {
            return;
        }
        Student user = new Student();
        user.setUsername(username.trim());
        user.setPassword(password.trim());
        user.login(new SaveListener<Student>() {
            @Override
            public void done(Student user, BmobException e) {
                if (e == null) {
                    listener.done(getCurrentUser(), null);
                } else {
                    listener.done(user,e);
                }
            }
        });
    }

    public void logout() {
        Global.resetGlobal();
        BmobUser.logOut();
    }
}
