package com.example.a97447.shop.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a97447.shop.MainActivity;
import com.example.a97447.shop.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {
    private ViewPager guide;
    private List texts=new ArrayList<TextView>();
    private Integer[] image={R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3,R.mipmap.guide4};
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.guide_activity);
        sp=GuideActivity.this.getSharedPreferences("login",MODE_PRIVATE);
        edit=sp.edit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Window window=getWindow();
        window.setStatusBarColor(Color.parseColor("#fdc00f"));
        texts.add(findViewById(R.id.yuan1));
        texts.add(findViewById(R.id.yuan2));
        texts.add(findViewById(R.id.yuan3));
        texts.add(findViewById(R.id.yuan4));
        guide=findViewById(R.id.guide);
        guide.setAdapter(new ImageAdapter());
        guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int a) {
                for(int i=0;i<texts.size();i++){
                    TextView tx=(TextView) texts.get(i);
                    tx.setBackgroundResource(R.mipmap.yuan);
                }
                TextView txs=(TextView) texts.get(a);
                txs.setBackgroundResource(R.mipmap.hyuan_fill);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    class ImageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view==o;
        }


        //将px转换为dp
        public int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue * scale + 0.5f);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            RelativeLayout tx=new RelativeLayout(GuideActivity.this);
            tx.setBackgroundResource(image[position]);
            if(position==3){
                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(px2dip(GuideActivity.this,180),px2dip(GuideActivity.this,40));
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                //layout_centerHorizontal
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.setMargins(0,0,0,px2dip(GuideActivity.this,46));
                Button btn=new Button(GuideActivity.this);
                btn.setBackgroundColor(Color.parseColor("#fdc00f"));
                btn.setText("开始体验");
                btn.setTextColor(Color.parseColor("#ffffff"));
                btn.setLayoutParams(params);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edit.putInt("is_login",1);
                        edit.commit();
                        startActivity(new Intent(GuideActivity.this,MainActivity.class));
                        finish();
                    }
                });
                tx.addView(btn);
            }
            container.addView(tx);

            return tx;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            RelativeLayout relative=(RelativeLayout)object;
            container.removeView(relative);
        }
    }
}
