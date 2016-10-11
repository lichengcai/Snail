package com.snail.utils;

import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import com.avos.avoscloud.okhttp.FormEncodingBuilder;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by chengcai on 2016/10/10.
 */

public class HttpUtils {
    private static HttpUtils mInstance;
    private OkHttpClient mOkHttpClient;

    private HttpUtils() {
        mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
//        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
//        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
//        //cookie enabled
//        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }

    private synchronized static HttpUtils getInstance() {
        if (mInstance == null)
            mInstance = new HttpUtils();
        return mInstance;
    }

    private void getRequest(String url, final LoadCallback loadCallback) {
        final Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                loadCallback.fail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                loadCallback.success(response.body().string());
            }
        });
    }

    private void postRequest(String url, List<Param> paramList, final LoadCallback loadCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : paramList) {
            builder.add(param.key,param.value);
        }
        RequestBody requestBody = builder.build();
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                loadCallback.fail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                loadCallback.success(response.body().string());
            }
        });
    }

    public static void test(String url, LoadCallback loadCallback) {
        getInstance().getRequest(url,loadCallback);
    }

    public static void post(String url,List<Param> params,LoadCallback loadCallback) {
        post(url,params,loadCallback);
    }
    /**
     * post请求参数类
     */
    public static class Param {

        String key;
        String value;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }

    public interface LoadCallback {
        void success(String json);
        void fail(IOException e);
    }

}
