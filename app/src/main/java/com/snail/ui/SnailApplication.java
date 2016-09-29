package com.snail.ui;

import android.app.Application;

import com.snail.ui.utils.SharedPreUtils;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class SnailApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreUtils.init(this);
    }
}
