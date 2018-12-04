package com.example.a97447.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a97447.shop.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends BaseFragment {
    private List texts=new ArrayList<TextView>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.user_fragment,container,false);
        init();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void init(){
        texts.add(view.findViewById(R.id.diyi));
        texts.add(view.findViewById(R.id.dier));
        texts.add(view.findViewById(R.id.disan));
        texts.add(view.findViewById(R.id.disi));
        texts.add(view.findViewById(R.id.diwu));
        texts.add(view.findViewById(R.id.diliu));
        texts.add(view.findViewById(R.id.diqi));
        for(int i=0;i<texts.size();i++){
            TextView tx=(TextView) texts.get(i);
            tx.setOnClickListener(new ItemListen());
        }
    }

    class ItemListen implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.diyi:

                    break;
                case R.id.dier:

                    break;
                case R.id.disan:

                    break;
                case R.id.disi:

                    break;
                case R.id.diwu:

                    break;
                case R.id.diliu:

                    break;
                case R.id.diqi:

                    break;
            }
        }
    }
}
