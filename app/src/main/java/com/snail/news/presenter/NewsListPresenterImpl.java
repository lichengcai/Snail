package com.snail.news.presenter;

import android.util.Log;

import com.avos.avoscloud.LogUtil;
import com.snail.common.Urls;
import com.snail.news.ActivityNews;
import com.snail.news.listener.OnLoadListener;
import com.snail.news.model.News;
import com.snail.news.model.NewsModel;
import com.snail.news.model.NewsModelImpl;
import com.snail.news.view.NewsListView;

import java.util.ArrayList;


/**
 * Created by lichengcai on 2016/10/11.
 */

public class NewsListPresenterImpl implements NewsListPresenter {
    private NewsModel mNewsModel;
    private NewsListView mNewsListView;

    public NewsListPresenterImpl(NewsListView mNewsListView) {
        this.mNewsListView = mNewsListView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void setNewsList(final int type, int pageIndex, final boolean refresh, final boolean loadMore) {
        if (refresh){
            pageIndex = 0;
        }
        String url = getUrl(type,pageIndex);
        Log.d("setNewsList","String url---" + url);
        mNewsModel.getNewsInfo(url, refresh, loadMore, new OnLoadListener() {
            @Override
            public void success(String json) {
                ArrayList<News> array = News.getNewsList(type,json);
                if (array != null) {
                    mNewsListView.setNews(array,refresh,loadMore);
                }

            }

            @Override
            public void fail(Exception e) {
                mNewsListView.setFail();
            }
        });
    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case ActivityNews.NEWS_EDUCATION:
                sb.append(Urls.COMMON_URL).append(Urls.EDUCATION_ID);
                break;
            case ActivityNews.NEWS_SCIENCE:
                sb.append(Urls.COMMON_URL).append(Urls.TECH_ID);
                break;
            case ActivityNews.NEWS_SPORTS:
                sb.append(Urls.COMMON_URL).append(Urls.SPORTS_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }


}
