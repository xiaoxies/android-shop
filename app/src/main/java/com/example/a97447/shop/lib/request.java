package com.example.a97447.shop.lib;

import android.arch.core.util.Function;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Date;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class request {
    private String httpUrl="http://192.168.1.162/index.php";
    private String sign="3db1ad6287faa5e1ebdc8000f6e58752";
    private long time=new Date().getTime();//时间戳
    private String url="";
    private FormBody.Builder build=new FormBody.Builder();
    private FormBody body=build.add("sign",sign).add("time",time+"").build();

    public request(String u){
        url=u;
    }

    //定义重写接口
    public interface BackHome{
        void success(String s);
        void error();
    }

    //get请求
    public void get(final Handler handler){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(httpUrl+url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("=============", "请求失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                JSONObject json=JSONObject.parseObject(response.body().string());
                int code=json.getIntValue("code");
                if(code==200){
                    msg.obj=json.getJSONArray("result");
                    msg.what=1;
                    handler.sendMessage(msg);
                }else{
                    msg.obj=json.getJSONArray("result");
                    msg.what=2;
                    handler.sendMessage(msg);
                }
            }
        });
    }


    //post请求
    public void post(final Handler handler){
        MediaType type = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody bodys=RequestBody.create(type,body.toString());
        Request request = new Request.Builder()
                .url(httpUrl+url)
                .post(bodys).build();
        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("请求失败", "onFailure: " + e.getMessage());
                //back.error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.w("请求成功", response.protocol() + " " +response.code() + " " + response.message());
                Message msg = new Message();
                JSONObject json=JSONObject.parseObject(response.body().string());
                int code=json.getIntValue("code");
                if(code==200){
                    msg.obj=json.getJSONArray("result");
                    msg.what=1;
                    handler.sendMessage(msg);
                }else{
                    msg.obj=json.getJSONArray("result");
                    msg.what=2;
                    handler.sendMessage(msg);
                }
            }
        });
    }
}
