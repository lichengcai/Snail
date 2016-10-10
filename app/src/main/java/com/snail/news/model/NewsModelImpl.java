package com.snail.news.model;

import android.util.Log;

import com.avos.avoscloud.okhttp.OkHttpClient;
import com.avos.avoscloud.okhttp.Request;
import com.avos.avoscloud.okhttp.Response;

import java.io.IOException;

/**
 * Created by chengcai on 2016/10/10.
 */

public class NewsModelImpl implements NewsModel {
    @Override
    public void getNewsInfo() {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().get().tag(this).url("http://c.m.163.com/nc/article/list/T1348654060988/0-20.html").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.d("tag","okhttp response---" + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
