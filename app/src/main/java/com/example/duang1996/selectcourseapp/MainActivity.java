package com.example.duang1996.selectcourseapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;
import com.example.duang1996.selectcourseapp.bean.Student;
import com.example.duang1996.selectcourseapp.fragement.HomeFragment;
import com.example.duang1996.selectcourseapp.fragement.Select_CourseFragment;
import com.example.duang1996.selectcourseapp.fragement.Select_ResultFragment;
import com.example.duang1996.selectcourseapp.fragement.TimetableFragment;
import com.example.duang1996.selectcourseapp.global.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView btnHome;
    private TextView btnSelect;
    private TextView btnTimetable;
    private TextView btnResult;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Fragment homeFragment;
    private Fragment select_courseFragment;
    private Fragment select_resultFragment;
    private Fragment timetableFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 控件初始化
        initViews();

        // 初始化底部tab栏
        setDefaultFragment();

        // 初始化Global中的两个列表
        initGlobalVariable();
    }

    private void initViews() {
        btnHome = findViewById(R.id.btn_home);
        btnSelect = findViewById(R.id.btn_select);
        btnTimetable = findViewById(R.id.btn_courses);
        btnResult = findViewById(R.id.btn_result);
        drawerLayout  =findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

        btnHome.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnTimetable.setOnClickListener(this);
        btnResult.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                BmobUserUtil.getInstance().logout();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
    }

    /**
     * set the default Fragment
     */
    private void setDefaultFragment() {
        switchFragment(0);
        //set the defalut tab state
        setTabState(btnHome, R.drawable.ic_home_light_green_500_24dp, getLocalColor(R.color.tab_active));
    }

    @Override
    public void onClick(View view) {
        resetTabState();
        switch (view.getId()) {
            case R.id.btn_home:
                setTabState(btnHome, R.drawable.ic_home_light_green_500_24dp, getLocalColor(R.color.tab_active));
                switchFragment(0);
                break;
            case R.id.btn_select:
                setTabState(btnSelect, R.drawable.ic_search_light_green_500_24dp, getLocalColor(R.color.tab_active));
                switchFragment(1);
                break;
            case R.id.btn_result:
                setTabState(btnResult, R.drawable.ic_playlist_add_check_light_green_500_24dp, getLocalColor(R.color.tab_active));
                switchFragment(2);
                break;
            case R.id.btn_courses:
                setTabState(btnTimetable, R.drawable.ic_date_range_light_green_500_24dp, getLocalColor(R.color.tab_active));
                switchFragment(3);
                break;
        }
    }

    private void switchFragment(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("home");
                }
                transaction.replace(R.id.sub_content, homeFragment);
                break;
            case 1:
                if (select_courseFragment == null) {
                    select_courseFragment = Select_CourseFragment.newInstance("select_course");
                }
                transaction.replace(R.id.sub_content, select_courseFragment);
                break;
            case 2:
                if (select_resultFragment == null) {
                    select_resultFragment = Select_ResultFragment.newInstance("select_result");
                }
                transaction.replace(R.id.sub_content, select_resultFragment);
                break;
            case 3:
                if (timetableFragment == null) {
                    timetableFragment = TimetableFragment.newInstance("timetable");
                }
                transaction.replace(R.id.sub_content, timetableFragment);
                break;
        }
        transaction.commit();
    }

    /**
     * set the tab state of bottom navigation bar
     *
     * @param textView the text to be shown
     * @param image    the image
     * @param color    the text color
     */
    private void setTabState(TextView textView, int image, int color) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, image, 0, 0);//Call requires API level 17
        textView.setTextColor(color);
    }

    /**
     * revert the image color and text color to black
     */
    private void resetTabState() {
        setTabState(btnHome, R.drawable.ic_home_grey_500_24dp, getLocalColor(R.color.black_1));
        setTabState(btnSelect, R.drawable.ic_search_grey_500_24dp, getLocalColor(R.color.black_1));
        setTabState(btnResult, R.drawable.ic_playlist_add_check_grey_500_24dp, getLocalColor(R.color.black_1));
        setTabState(btnTimetable, R.drawable.ic_date_range_grey_500_24dp, getLocalColor(R.color.black_1));
    }

    /**
     * @param i the color id
     * @return color
     */
    private int getLocalColor(int i) {
        return getApplicationContext().getResources().getColor(i);
    }

    // 初始化Global中的两个列表
    private void initGlobalVariable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Student user = BmobUserUtil.getInstance().getCurrentUser();
                BmobUtil instance = BmobUtil.getInstance();

                // 获取Lesson列表
                Global.addAllLessonList(instance.getAllLessons());


                // 获取可选择课程列表
                Global.addAllSelectableCourseList(instance.getSelectableCourseList(user.getMajor(), 2017, 2));

                // 获取已选择课程列表
                List<String> list = new ArrayList<>();
                list.addAll(instance.getSelectedCourseObjectIdList(user.getObjectId()));
                if(list.size() != 0) {
                    for(String objectId : list) {
                        Global.addSelectedCourseList(instance.fromObjectIdToCourse(objectId));
                    }
                }
                // 获取待筛选课程列表
                list = new ArrayList<>();
                list.addAll(instance.getSelectingCourseObjectIdList(user.getObjectId()));
                if(list.size() != 0) {
                    for(String objectId : list) {
                        Global.addSelectingCourseList(instance.fromObjectIdToCourse(objectId));
                    }
                }
                // 去除selectableCourseList中和selectedCourseList中共有的元素
                for(int i = 0; i < Global.getSelectedCourseListSize(); i++) {
                    for(int j = 0; j < Global.getSelectableCourseListSize();) {
                        if(Global.getSelectableCourse(j).getObjectId().equals(Global.getSelectedCourse(i).getObjectId())) {
                            Global.removeSelectableCourseList(j);
                        } else {
                            j++;
                        }
                    }
                }

                // 去除selectableCourseList中和selectingCourseList中共有的元素
                for(int i = 0; i < Global.getSelectingCourseListSize(); i++) {
                    for(int j = 0; j < Global.getSelectableCourseListSize();) {
                        if(Global.getSelectableCourse(j).getObjectId().equals(Global.getSelectingCourse(i).getObjectId())) {
                            Global.removeSelectableCourseList(j);
                        } else {
                            j++;
                        }
                    }
                }
            }
        }).start();
    }
}
