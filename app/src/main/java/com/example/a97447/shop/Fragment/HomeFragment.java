package com.example.a97447.shop.Fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.example.a97447.shop.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import com.example.a97447.shop.lib.*;

public class HomeFragment extends BaseFragment {
    private Integer[] images={R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4};
    private List image=new ArrayList<Bitmap>();
    private Banner banner;
    private TextView scan;
    private View view;
    private int number=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_fragment,container,false);
        scan=view.findViewById(R.id.scan);
        startBanner();//轮播图部分
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void startBanner(){
        banner=view.findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader());
        request ajax=new request("/app/index/get_banner_list");
        ajax.get(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 1:
                        if(isAdded()) {
                            final JSONArray array = JSONArray.parseArray(msg.obj.toString());
                            for (int i = 0; i < array.size(); i++) {
                                JSONObject json = array.getJSONObject(i);
                                final Handler hand = new Handler() {
                                    @Override
                                    public void handleMessage(Message msg) {
                                        number += 1;
                                        Bitmap bit = (Bitmap) msg.obj;
                                        image.add(bit);
                                        if (number == array.size()) {
                                            banner.setImages(image);
                                            banner.setDelayTime(4000);
                                            banner.start();
                                        }
                                    }
                                };
                                bitmap.getHttpBitmap(json.getString("image"), hand);
                            }
                        }
                        break;
                    default:

                }
            }
        });

    }
    class BannerImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap((Bitmap) path);
        }
    }
}
