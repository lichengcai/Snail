package com.snail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FirstPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;

    public FirstPagerAdapter(FragmentManager fm,ArrayList<Fragment> arrayList) {
        super(fm);
        mFragments = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
