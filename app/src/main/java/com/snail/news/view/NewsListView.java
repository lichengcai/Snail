package com.snail.news.view;

import com.snail.news.model.News;

import java.util.ArrayList;

/**
 * Created by chengcai on 2016/10/10.
 */

public interface NewsListView {

    void setNews(ArrayList<News> data);
    void setFail();
}
