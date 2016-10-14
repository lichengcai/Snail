package com.snail.mvp.wrong.persenter;

import com.snail.mvp.listener.OnUploadListener;
import com.snail.mvp.wrong.model.WrongModel;
import com.snail.mvp.wrong.model.WrongModelImpl;
import com.snail.mvp.wrong.view.WrongView;

import java.io.File;

/**
 * Created by lichengcai on 2016/10/14.
 */

public class WrongPresenterImpl implements WrongPresenter {
    private WrongModel mWrongModel;
    private WrongView mWrongView;

    public WrongPresenterImpl(WrongView wrongView) {
        this.mWrongView = wrongView;
        mWrongModel = new WrongModelImpl();
    }

    @Override
    public void uploadImageFile(File file,String title,String content) {
        mWrongModel.uploadImgFile(file, title, content, new OnUploadListener() {
            @Override
            public void success() {
                mWrongView.showUploadSuccess();
            }

            @Override
            public void fail() {
                mWrongView.showUploadFail();
            }
        });
    }

}
