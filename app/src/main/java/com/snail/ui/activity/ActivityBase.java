package com.snail.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class ActivityBase extends FragmentActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
//        ButterKnife.bind(this);
    }
}
