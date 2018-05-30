package com.example.duang1996.selectcourseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.duang1996.selectcourseapp.bean.Student;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {
    private EditText netId;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initViews();

        /*
         * 检测本地登录状态，如果已经登录，则直接进入mainActivity
         */
    }

    private void initViews() {
        netId = findViewById(R.id.netIdInput);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUserUtil.getInstance().login(netId.getText().toString(), password.getText().toString(), new LogInListener() {
                    @Override
                    public void done(Object o, BmobException e) {
                        if(e == null) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {

                        }
                    }
                });
            }
        });
    }
}
