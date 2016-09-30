package com.snail.ui.activity;

import android.os.Bundle;

import com.snail.R;

import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class ActivitySplash extends ActivityBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }


}
