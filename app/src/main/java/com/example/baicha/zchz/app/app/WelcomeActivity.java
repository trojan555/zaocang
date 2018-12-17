package com.example.baicha.zchz.app.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.baicha.zchz.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //2秒钟进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行是在主线程
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
