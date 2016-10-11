package com.snail.news.listener;

/**
 * Created by lichengcai on 2016/10/11.
 */

public interface OnLoadListener {
    void success(String json);
    void fail(Exception e);
}
