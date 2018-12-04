package com.example.a97447.shop;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.a97447.shop.Activity.BaseActivity;
import com.example.a97447.shop.Fragment.BrandFragment;
import com.example.a97447.shop.Fragment.CartFragment;
import com.example.a97447.shop.Fragment.HomeFragment;
import com.example.a97447.shop.Fragment.NotLoginFragment;
import com.example.a97447.shop.Fragment.UserFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List Layouts=new ArrayList<LinearLayout>();
    private List icons=new ArrayList<TextView>();
    private List texts=new ArrayList<TextView>();
    private Integer[] bg={R.mipmap.home,R.mipmap.cart,R.mipmap.sale,R.mipmap.user};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Window window=getWindow();
        window.setStatusBarColor(Color.parseColor("#E71F19"));
    }

    public void init(){
        super.replace(R.id.fragment,new HomeFragment());
        Layouts.add((LinearLayout)findViewById(R.id.home_layout));
        Layouts.add((LinearLayout)findViewById(R.id.cart_layout));
        Layouts.add((LinearLayout)findViewById(R.id.brand_layout));
        Layouts.add((LinearLayout)findViewById(R.id.user_layout));

        icons.add((TextView)findViewById(R.id.home_bg));
        icons.add((TextView)findViewById(R.id.cart_bg));
        icons.add((TextView)findViewById(R.id.brand_bg));
        icons.add((TextView)findViewById(R.id.user_bg));

        texts.add((TextView)findViewById(R.id.home_text));
        texts.add((TextView)findViewById(R.id.cart_text));
        texts.add((TextView)findViewById(R.id.brand_text));
        texts.add((TextView)findViewById(R.id.user_text));
        //给每个底部导航栏加上点击事件
        for(int i=0;i<Layouts.size();i++){
            LinearLayout layout=(LinearLayout) Layouts.get(i);
            layout.setOnClickListener(new LayoutListen());
        }
    }

    class LayoutListen implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.home_layout:
                    for(int i=0;i<bg.length;i++){
                        TextView icon1=(TextView) icons.get(i);
                        TextView text1=(TextView) texts.get(i);
                        icon1.setBackgroundResource(bg[i]);
                        text1.setTextColor(Color.parseColor("#929292"));
                    }
                    TextView icon=(TextView) icons.get(0);
                    TextView text=(TextView) texts.get(0);
                    icon.setBackgroundResource(R.mipmap.home_fill);
                    text.setTextColor(Color.parseColor("#E71F19"));
                    MainActivity.super.replace(R.id.fragment,new HomeFragment());
                    break;
                case R.id.cart_layout:
                    for(int i=0;i<bg.length;i++){
                        TextView icon2=(TextView) icons.get(i);
                        TextView text2=(TextView) texts.get(i);
                        icon2.setBackgroundResource(bg[i]);
                        text2.setTextColor(Color.parseColor("#929292"));
                    }
                    TextView iconss=(TextView) icons.get(1);
                    TextView textss=(TextView) texts.get(1);
                    iconss.setBackgroundResource(R.mipmap.cart_fill);
                    textss.setTextColor(Color.parseColor("#E71F19"));
                    MainActivity.super.replace(R.id.fragment,new CartFragment());
                    break;
                case R.id.brand_layout:
                        for(int i=0;i<bg.length;i++){
                            TextView icon3=(TextView) icons.get(i);
                            TextView text3=(TextView) texts.get(i);
                            icon3.setBackgroundResource(bg[i]);
                            text3.setTextColor(Color.parseColor("#929292"));
                        }
                        TextView icon3=(TextView) icons.get(2);
                        TextView text3=(TextView) texts.get(2);
                        icon3.setBackgroundResource(R.mipmap.sale_fill);
                        text3.setTextColor(Color.parseColor("#E71F19"));
                        MainActivity.super.replace(R.id.fragment,new BrandFragment());
                    break;
                case R.id.user_layout:
                    for(int i=0;i<bg.length;i++){
                        TextView icon4=(TextView) icons.get(i);
                        TextView text4=(TextView) texts.get(i);
                        icon4.setBackgroundResource(bg[i]);
                        text4.setTextColor(Color.parseColor("#929292"));
                    }
                    TextView icon4=(TextView) icons.get(3);
                    TextView text4=(TextView) texts.get(3);
                    icon4.setBackgroundResource(R.mipmap.user_fill);
                    text4.setTextColor(Color.parseColor("#E71F19"));
                    MainActivity.super.replace(R.id.fragment,new NotLoginFragment());
                    break;
            }
        }
    }
}
