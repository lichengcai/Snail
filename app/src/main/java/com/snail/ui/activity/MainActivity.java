package com.snail.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Process;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.snail.R;
import com.snail.ui.adapter.FirstPagerAdapter;
import com.snail.ui.fragment.FragmentHome;
import com.snail.ui.fragment.FragmentMore;
import com.snail.ui.fragment.FragmentWrite;
import com.snail.ui.transforms.CubeOutTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivityBase {
    @BindView(R.id.vp_home)
    ViewPager mViewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.mFloatingActionButton)
    FloatingActionButton mFloatingActionButton;
    /**
     * Fragment集合
     */
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    /**
     * ViewPager 适配器
     */
    FirstPagerAdapter mAdapter;
    /**
     * 首次点击返回键时间
     */
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setAllListener();

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.btn_tab_home_normal, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.btn_tab_wrong_normal, "Write"))
                .addItem(new BottomNavigationItem(R.drawable.btn_tab_more_normal, "More")).initialise();
        mFragmentList.add(new FragmentHome());
        mFragmentList.add(new FragmentWrite());
        mFragmentList.add(new FragmentMore());

        mAdapter = new FirstPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        try {
            mViewPager.setPageTransformer(true, new TransformerItem(CubeOutTransformer.class).clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * 设置监听事件
     */
    private void setAllListener() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FloatingActionButton", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.d("tag", "on tab selected---" + position);
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bitmap b = data.getParcelableExtra("data");
            }
        }
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    /**
     * 退出程序
     */
    public void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            Process.killProcess(Process.myPid());
        }
    }

    /**
     * ViewPager动画辅助类
     */
    private static final class TransformerItem {
        final String title;
        final Class<? extends ViewPager.PageTransformer> clazz;

        TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
            this.clazz = clazz;
            title = clazz.getSimpleName();
        }

        @Override
        public String toString() {
            return title;
        }

    }
}
