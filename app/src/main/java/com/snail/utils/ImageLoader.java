package com.snail.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.snail.R;

/**
 * Created by lichengcai on 2016/10/11.
 */

public class ImageLoader {
    private static ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (com.nostra13.universalimageloader.core.ImageLoader.class){
                mInstance = new ImageLoader();
            }
        }
        return mInstance;
    }

    public void displayImage(Context context, String imgUrl, ImageView imageView) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();

        DisplayImageOptions mOptions = null;
            mOptions = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_loading)
                    .showImageForEmptyUri(R.drawable.ic_load_fail)
                    .showImageOnFail(R.drawable.ic_load_fail)
                    .imageScaleType(ImageScaleType.EXACTLY).cacheInMemory(true)
                    .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        com.nostra13.universalimageloader.core.ImageLoader imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.init(configuration);
        imageLoader.displayImage(imgUrl, imageView, mOptions, new SimpleImageLoadingListener());
    }
}
