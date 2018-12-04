package com.example.a97447.shop.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class bitmap {
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
}
