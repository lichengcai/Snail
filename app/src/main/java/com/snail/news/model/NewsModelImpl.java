package com.snail.news.model;

import android.util.Log;

import com.avos.avoscloud.okhttp.OkHttpClient;
import com.avos.avoscloud.okhttp.Request;
import com.avos.avoscloud.okhttp.Response;
import com.snail.utils.HttpUtils;

import java.io.IOException;

/**
 * Created by chengcai on 2016/10/10.
 */

public class NewsModelImpl implements NewsModel {
    @Override
    public void getNewsInfo() {

        HttpUtils.test("http://c.m.163.com/nc/article/list/T1348654060988/0-20.html", new HttpUtils.LoadCallback() {
            @Override
            public void success(String json) {
                Log.d("test","httpUtils response---" + json);
            }

            @Override
            public void fail(IOException e) {

            }
        });

    }
}
