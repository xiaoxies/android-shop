package com.example.a97447.shop.Fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.a97447.shop.R;
import com.example.a97447.shop.lib.request;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrandFragment extends BaseFragment {
    private View view;
    private TableLayout table;
    private float coulm = 3;//table列数
    private Integer[] bg = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4, R.mipmap.bottom, R.mipmap.card_error, R.mipmap.card_success, R.mipmap.cart, R.mipmap.cart_fill, R.mipmap.banner1, R.mipmap.banner1, R.mipmap.banner1, R.mipmap.banner1};
    private int width;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.brand_fragment, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    //将px转换为dp
    public final int px2dip(float pxValue) {
        if(isAdded()){
            final float scale = BrandFragment.this.getResources().getDisplayMetrics().density;
            return (int) (pxValue * scale + 0.5f);
        }
        return 0;
    }

    //获取图片资源
    public static void getHttpBitmap(final String url,final Handler hand) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL myFileURL;
                    Bitmap bitmap = null;
                    myFileURL = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
                    conn.setConnectTimeout(6000);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    Message msg=new Message();
                    msg.obj=bitmap;
                    hand.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void init() {
        table = view.findViewById(R.id.table);


        DisplayMetrics dm = getResources().getDisplayMetrics();
        Activity activity = getActivity();
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;//获取屏幕宽度

        request ajax = new request("/app/index/get_brand_list");
        ajax.get( new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        if(isAdded()){  //isAdded判断当前的fragment被加载到activity中
                            JSONArray arr = JSONArray.parseArray(msg.obj.toString());
                            //动态往TableLayout添加元素
                            for (int i = 0; i < Math.ceil(arr.size() / coulm); i++) {
                                TableLayout.LayoutParams tparams = new TableLayout.LayoutParams();
                                tparams.setMargins(0, 0, 0, px2dip(10));
                                TableRow row = new TableRow(getContext());
                                row.setLayoutParams(tparams);
                                for (int a = (i * 3); a < (i * 3 + 3); a++) {
                                    if (a < arr.size()) {
                                        final JSONObject obj = arr.getJSONObject(a);
                                        Log.i("--------", obj.get("logo")+"");
                                        if (obj.get("logo") != "") {
                                            final ImageView image = new ImageView(getContext());
                                            final LinearLayout layout = new LinearLayout(getContext());
                                            TableRow.LayoutParams params = new TableRow.LayoutParams();
                                            params.width = (width - 80) / 3;
                                            params.height = px2dip(70);
                                            params.setMargins(0, 0, px2dip( 10), 0);
                                            layout.setLayoutParams(params);
                                            layout.setGravity(Gravity.CENTER);
                                            layout.setBackgroundResource(R.drawable.ripple);

                                            //动态添加点击水波纹效果
//                                            RippleDrawable draw=(RippleDrawable)getContext().getResources().getDrawable(R.drawable.ripple);
//                                            layout.setForeground(draw);
//                                            layout.setClickable(true);

                                            image.setAdjustViewBounds(true);
                                            image.setScaleType(ImageView.ScaleType.FIT_XY);
                                            layout.setId(a);
                                            //tx.setTag(a+"|"+bg[a]);//设置标识
                                            layout.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Log.i("%%%%%%%%%%%","sddddddddd");
                                                }
                                            });

                                            Handler hand=new Handler(){
                                                @Override
                                                public void handleMessage(Message msg) {
                                                    Bitmap bm=(Bitmap) msg.obj;
                                                    image.setImageBitmap(bm);

                                                }
                                            };
                                            getHttpBitmap(obj.get("logo")+"",hand);

                                            layout.addView(image);
                                            row.addView(layout);
                                        }

                                    }

                                }
                                table.addView(row);
                            }
                        }

                        break;
                    default:

                        break;
                }
            }
        });

    }
}
