package com.snail.mvp.listener;

import com.avos.avoscloud.AVObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lichengcai on 2016/10/14.
 */

public interface OnQueryListener {
    void onQuerySuccess(List<AVObject> avObjects);
    void onQueryFail();
}
