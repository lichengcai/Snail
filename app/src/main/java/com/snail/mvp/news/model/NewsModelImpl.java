package com.snail.mvp.news.model;

import android.util.Log;

import com.snail.mvp.news.listener.OnLoadListener;
import com.snail.utils.HttpUtils;

import java.io.IOException;

/**
 * Created by chengcai on 2016/10/10.
 */

public class NewsModelImpl implements NewsModel {

    @Override
    public void getNewsInfo(String url, boolean refresh, boolean loadMore, final OnLoadListener onLoadListener) {
        HttpUtils.get(url, new HttpUtils.LoadCallback() {
            @Override
            public void success(String json) {
                Log.d("test","httpUtils response---" + json);
                onLoadListener.success(json);
            }

            @Override
            public void fail(IOException e) {
                onLoadListener.fail(e);
            }
        });
    }
}
