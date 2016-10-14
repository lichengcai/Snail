package com.snail.mvp.wrong.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.snail.mvp.listener.OnUploadFileListener;
import com.snail.mvp.listener.OnUploadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by lichengcai on 2016/10/14.
 */

public class WrongModelImpl implements WrongModel {
    @Override
    public void uploadImgFile(final File file, final String title, final String content, final OnUploadListener onUploadListener) {
        try {
            final AVFile avfile = AVFile.withAbsoluteLocalPath("snail.png", file.getPath());
            avfile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        AVObject avObject = new AVObject("WrongBean");
                        avObject.put("imgUrl",avfile.getUrl());
                        avObject.put("title",title);
                        avObject.put("content",content);
                        uploadWrongBean(avObject,onUploadListener);
                    }else {

                    }
                }
            },new ProgressCallback() {
                @Override
                public void done(Integer integer) {
                    Log.d("upload",integer.toString());
                    // 上传进度数据，integer 介于 0 和 100。
                }
            });
        } catch (FileNotFoundException e) {
            Log.d("upload-exception", e.toString());
            e.printStackTrace();
        }
    }

    private void uploadWrongBean(AVObject avObject, final OnUploadListener onUploadListener) {
        avObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    onUploadListener.success();
                }else {
                    onUploadListener.fail();
                }
            }
        });
    }
}
