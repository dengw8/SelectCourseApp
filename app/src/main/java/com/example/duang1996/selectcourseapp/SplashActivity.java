package com.example.duang1996.selectcourseapp;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.example.duang1996.selectcourseapp.util.BmobUserUtil;
import com.example.duang1996.selectcourseapp.util.BmobUtil;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import cn.bmob.v3.BmobUser;


public class SplashActivity extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!


    @Override
    public void initSplash(ConfigSplash configSplash) {

        /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimaryDark); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.xh); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeInDown); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Title
        configSplash.setTitleSplash("中大选课");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(25f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.StandUp);
        // configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
        BmobUtil.getInstance().initializeApp(this);
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
}

