package com.snail.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by chengcai on 2016/10/13.
 */

public class BitmapUtils {
    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId,int reqWidth, int reqHeight) {
        //First decode with inJustDecodeBounds = true to check dimensions;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        //Calculate imSampleSize
//        options.inSampleSize =
        return null;
    }

    public static int calculateInsampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        return 0;
    }
}
