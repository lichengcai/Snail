package com.snail.ui;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.snail.ui.utils.SharedPreUtils;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class SnailApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreUtils.init(this);

        // this appid, appkey 这里的appkey 是具体应用的key
        AVOSCloud.initialize(this, "3wWw917QhhjFgz8CrIUuMnhK-gzGzoHsz", "uKedxWMG4fnrPbI4v7ToXfbv");
    }
}
