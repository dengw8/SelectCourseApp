package com.example.duang1996.selectcourseapp;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duang1996.selectcourseapp.fragement.HomeFragment;
import com.example.duang1996.selectcourseapp.fragement.Select_CourseFragment;
import com.example.duang1996.selectcourseapp.fragement.Select_ResultFragment;
import com.example.duang1996.selectcourseapp.fragement.TimetableFragment;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, Select_CourseFragment.OnFragmentInteractionListener,
        Select_ResultFragment.OnFragmentInteractionListener, TimetableFragment.OnFragmentInteractionListener{

    private TextView btnHome;
    private TextView btnSelect;
    private TextView btnTimetable;
    private TextView btnResult;

    private TextView[] mTabs;

    private Fragment homeFragment;
    private Fragment select_courseFragment;
    private Fragment select_resultFragment;
    private Fragment timetableFragment;

    private Fragment mCurrentFragment = null;

    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        initViews();

        initTabs();
    }

    private void initViews() {
        btnHome = findViewById(R.id.btn_home);
        btnSelect = findViewById(R.id.btn_select);
        btnTimetable = findViewById(R.id.btn_courses);
        btnResult = findViewById(R.id.btn_result);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initTabs() {
        mTabs = new TextView[4];
        mTabs[0] = btnHome;
        mTabs[1] = btnSelect;
        mTabs[2] = btnTimetable;
        mTabs[3] = btnResult;

        for(TextView textView : mTabs) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTabSelect(v);
                }
            });
        }

        onTabSelect(btnHome);
    }

    public void onTabSelect(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (view.getId()) {
            case R.id.btn_home:
                index = 0;
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fragment_container,homeFragment);
                } else {
                    transaction.show(homeFragment);
                    mCurrentFragment = homeFragment;
                }
                break;
            case R.id.btn_select:
                index = 1;
                if (select_courseFragment == null) {
                    select_courseFragment = new Select_CourseFragment();
                    transaction.add(R.id.fragment_container, select_courseFragment);
                } else {
                    transaction.show(select_courseFragment);
                    mCurrentFragment = select_courseFragment;
                };
                break;
            case R.id.btn_courses:
                index = 2;
                if (timetableFragment == null) {
                    timetableFragment = new TimetableFragment();
                    transaction.add(R.id.fragment_container, timetableFragment);
                } else {
                    transaction.show(timetableFragment);
                    mCurrentFragment = timetableFragment;
                }
                break;
            case R.id.btn_result:
                index = 3;
                if (select_resultFragment == null) {
                    select_resultFragment = new Select_ResultFragment();
                    transaction.add(R.id.fragment_container, select_resultFragment);
                } else {
                    transaction.show(select_resultFragment);
                    mCurrentFragment = select_resultFragment;
                }
                break;
            default:
                break;
        }
        onTabIndex(index);
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction) {
        if(homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (select_courseFragment != null) {
            transaction.hide(select_courseFragment);
        }
        if (timetableFragment != null) {
            transaction.hide(timetableFragment);
        }
        if (select_resultFragment != null) {
            transaction.hide(select_resultFragment);
        }
    }

    private void onTabIndex(int index) {
        //设置属性，更换文本颜色和图片颜色
        //相关的xml在drawable目录下
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
