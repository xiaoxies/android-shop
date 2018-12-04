package com.example.a97447.shop.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.a97447.shop.MainActivity;
import com.example.a97447.shop.R;

public class StartActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.start_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Window window=getWindow();
        window.setStatusBarColor(Color.parseColor("#fdc00f"));


        SharedPreferences sp = StartActivity.this.getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getInt("is_login",0)==0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(StartActivity.this,GuideActivity.class));
                    finish();
                }
            },2000);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(StartActivity.this,MainActivity.class));
                    finish();

                }
            },2000);
        }


    }
}
