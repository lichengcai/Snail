package com.snail.mvp.news.model;

import com.snail.mvp.news.listener.OnLoadListener;

/**
 * Created by chengcai on 2016/10/10.
 */

public interface NewsModel {
    void getNewsInfo(String url, boolean refresh, boolean loadMore, OnLoadListener onLoadListener);
}
