package com.snail.mvp.listener;

import android.graphics.Bitmap;

/**
 * Created by lichengcai on 2016/10/14.
 */

public interface OnUploadFileListener {
    void success(Bitmap bitmap);
    void fail();
}
