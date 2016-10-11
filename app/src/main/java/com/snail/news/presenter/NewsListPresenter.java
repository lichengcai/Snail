package com.snail.news.presenter;

import com.snail.news.model.News;

import java.util.ArrayList;

/**
 * Created by chengcai on 2016/10/10.
 */

public interface NewsListPresenter {
    void setNewsList(int type,int pageIndex,boolean refresh, boolean loadMore);
}
