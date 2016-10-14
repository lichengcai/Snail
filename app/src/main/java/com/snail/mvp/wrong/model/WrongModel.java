package com.snail.mvp.wrong.model;

import com.avos.avoscloud.AVObject;
import com.snail.mvp.listener.OnUploadListener;

import java.io.File;

/**
 * Created by lichengcai on 2016/10/14.
 */

public interface WrongModel {
    void uploadImgFile(File file,String title,String content,OnUploadListener onUploadListener);
}
