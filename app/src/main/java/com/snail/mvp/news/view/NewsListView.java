package com.snail.mvp.news.view;

import com.snail.mvp.news.model.News;

import java.util.ArrayList;

/**
 * Created by chengcai on 2016/10/10.
 */

public interface NewsListView {

    void setNews(ArrayList<News> data, boolean refresh, boolean loadMore);
    void setFail();
}
