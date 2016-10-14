package com.snail.mvp.wrong.view;

import android.graphics.Bitmap;

import com.snail.mvp.wrong.model.WrongBean;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/14.
 */

public interface WrongView {
    void setWrongImage(Bitmap bitmap);
    void showUploadSuccess();
    void showUploadFail();

    void setWrongBeanSuccess(ArrayList<WrongBean> arrayList);
    void setWrongBeanFail();
    void setWrongBeanEmpty();
}
