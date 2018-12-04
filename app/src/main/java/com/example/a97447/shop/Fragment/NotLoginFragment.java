package com.example.a97447.shop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a97447.shop.Activity.LoginActivity;
import com.example.a97447.shop.MainActivity;
import com.example.a97447.shop.R;

public class NotLoginFragment extends BaseFragment {
    private View view;
    private Button login;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.not_login_fragment,container,false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        login=view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),LoginActivity.class));
            }
        });
    }
}
