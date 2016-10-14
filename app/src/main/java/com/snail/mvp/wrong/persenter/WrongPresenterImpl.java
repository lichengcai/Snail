package com.snail.mvp.wrong.persenter;

import com.avos.avoscloud.AVObject;
import com.snail.mvp.listener.OnQueryListener;
import com.snail.mvp.listener.OnUploadListener;
import com.snail.mvp.wrong.model.WrongBean;
import com.snail.mvp.wrong.model.WrongModel;
import com.snail.mvp.wrong.model.WrongModelImpl;
import com.snail.mvp.wrong.view.WrongView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void queryWrongBean() {
        mWrongModel.getWrongInfo(new OnQueryListener() {
            @Override
            public void onQuerySuccess(List<AVObject> avObjects) {
                ArrayList<WrongBean> arrayList = new ArrayList<>();
                if (avObjects.size() == 0) {
                    mWrongView.setWrongBeanEmpty();
                }else {
                    for (int i=0; i<avObjects.size(); i++) {
                        WrongBean wrongBean = new WrongBean();
                        wrongBean.setContent(avObjects.get(i).get("content").toString());
                        wrongBean.setTitle(avObjects.get(i).get("title").toString());
                        wrongBean.setImgUrl(avObjects.get(i).get("imgUrl").toString());

                        arrayList.add(wrongBean);
                    }


                    mWrongView.setWrongBeanSuccess(arrayList);
                }

            }

            @Override
            public void onQueryFail() {
                mWrongView.setWrongBeanFail();
            }
        });
    }

}
