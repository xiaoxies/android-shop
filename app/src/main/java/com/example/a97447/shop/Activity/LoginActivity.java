package com.example.a97447.shop.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a97447.shop.R;

public class LoginActivity extends BaseActivity{
    private TextView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.login_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("+++++++","sdsdsds");
                finish();
            }
        });
    }
}
