package com.snail.mvp.news.presenter;

/**
 * Created by chengcai on 2016/10/10.
 */

public interface NewsListPresenter {
    void setNewsList(int type,int pageIndex,boolean refresh, boolean loadMore);
}
