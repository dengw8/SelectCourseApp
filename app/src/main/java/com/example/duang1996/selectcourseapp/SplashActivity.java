package com.example.duang1996.selectcourseapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Student;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        BmobUtil.getInstance().initializeApp(this);

        Handler handler= new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                BmobUser user = BmobUserUtil.getInstance().getCurrentUser();
                Intent intent;
                if(user == null) {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, 2000);
    }
}
