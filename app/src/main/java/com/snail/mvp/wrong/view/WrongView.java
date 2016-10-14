package com.snail.mvp.wrong.view;

import android.graphics.Bitmap;

/**
 * Created by lichengcai on 2016/10/14.
 */

public interface WrongView {
    void setWrongImage(Bitmap bitmap);
    void showUploadSuccess();
    void showUploadFail();
}
