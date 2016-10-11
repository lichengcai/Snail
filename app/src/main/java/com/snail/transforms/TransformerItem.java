package com.snail.transforms;

import android.support.v4.view.ViewPager;

/**
 * Created by lichengcai on 2016/10/11.
 */

public class TransformerItem {
    final String title;
    public final Class<? extends ViewPager.PageTransformer> clazz;

    public TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
        this.clazz = clazz;
        title = clazz.getSimpleName();
    }

    @Override
    public String toString() {
        return title;
    }
}
