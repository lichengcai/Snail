package com.snail.news.model;

import com.snail.news.listener.OnLoadListener;

/**
 * Created by chengcai on 2016/10/10.
 */

public interface NewsModel {
    void getNewsInfo(String url, boolean refresh, boolean loadMore, OnLoadListener onLoadListener);
}
