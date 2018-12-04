package com.example.a97447.shop.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void replace(int i, Fragment f){
        //每次点击的时候要重新beginTransaction，所有在父类声明
        getSupportFragmentManager().beginTransaction().replace(i,f).commit();

    }
}
