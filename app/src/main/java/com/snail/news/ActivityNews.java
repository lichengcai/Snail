package com.snail.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.snail.R;
import com.snail.ui.activity.ActivityBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chengcai on 2016/10/10.
 */

public class ActivityNews extends ActivityBase {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static final int NEWS_EDUCATION = 0;
    public static final int NEWS_SCIENCE = 1;
    public static final int NEWS_SPORTS = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initView();

        NewsPagerAdapter adapter = new NewsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentListNews.newInstance(NEWS_EDUCATION),getString(R.string.education));
        adapter.addFragment(FragmentListNews.newInstance(NEWS_SCIENCE),getString(R.string.science));
        adapter.addFragment(FragmentListNews.newInstance(NEWS_SPORTS),getString(R.string.sport));

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.education));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.science));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.sport));

    }

    public static class NewsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
