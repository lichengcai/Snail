package com.snail.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.os.Process;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.snail.R;
import com.snail.adapter.FirstPagerAdapter;
import com.snail.transforms.DefaultTransformer;
import com.snail.transforms.TransformerItem;
import com.snail.ui.fragment.FragmentHome;
import com.snail.ui.fragment.FragmentMore;
import com.snail.ui.fragment.FragmentWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivityBase {
    private static final int REQUEST_CODE_CAMERA = 0;
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
        
        testNews();
    }

    private void testNews() {
        AVObject a = new AVObject("News");
        a.add("title","test_title");
        a.add("ptime","test_ptime");
        a.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d("testNews","news save successful");
                }else {
                    Log.d("testNews","news save failed");
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);


    }

    /**
     * 初始化
     */
    private void init() {
        setAllListener();

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.btn_tab_home_normal, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.btn_tab_wrong_normal, "Wrong"))
                .addItem(new BottomNavigationItem(R.drawable.btn_tab_more_normal, "More")).initialise();
        mFragmentList.add(new FragmentHome());
        mFragmentList.add(new FragmentWrite());
        mFragmentList.add(new FragmentMore());

        mAdapter = new FirstPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        try {
            mViewPager.setPageTransformer(true, new TransformerItem(DefaultTransformer.class).clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();

        }
        initBindEvent();
    }

    /**
     * 测试 leancloud 插入数据的操作
     */
    private void initBindEvent() {
//        btn.setOnClickListener(newnote View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 测试 SDK 是否正常工作的代码
//                Log.d("btnclick","btnclick!");
//                AVObject testObject = newnote AVObject("_User");
//                testObject.put("username","shibiaoz");
//                testObject.put("password","123");
//                testObject.put("mobilePhoneNumber","18513622821");
//                testObject.saveInBackground(newnote SaveCallback() {
//                    @Override
//                    public void done(AVException e) {
//                        Log.d("btndone","btnclick-----");
//                        if(e == null){
//                            Log.d("saved","success!");
//                        }
//                    }
//                });
//            }
//        });

//        imgBtn.setOnClickListener(newnote View.OnClickListener() {
//            /**
//             * uploadfile from network
//             * @param v
//             */
//            @Override
//            public void onClick(View v) {
//                /**
//                 * below code is test code,
//                 * in some case,must define sure file suffix
//                 * @params sure filename,you should get suffix from http img url
//                 * @parms sure http url,
//                 */
//                final AVFile file = newnote AVFile("test.gif", "http://ww3.sinaimg.cn/bmiddle/596b0666gw1ed70eavm5tg20bq06m7wi.gif", newnote HashMap<String, Object>());
//                file.saveInBackground(newnote SaveCallback() {
//                    @Override
//                    public void done(AVException e) {
//                        Log.d("savefile", file.getUrl());//返回一个唯一的 Url 地址
//                    }
//                });
//            }
//        });
    }


    /**
     * 设置监听事件
     */
    private void setAllListener() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"FloatingActionButton",Toast.LENGTH_LONG).show();
//                Intent intent = newnote Intent();
//                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                MainActivity.this.startActivityForResult(intent,REQUEST_CODE_CAMERA);

                Uri imageUri = Uri.fromFile(getTempImage());
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 指定存储照片的路径

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
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
            if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
                File file = getTempImage();
                String filePath = file.getPath();
                Log.d("MainActivity","file ---" + file.length());
                uploadCamerImage(filePath, file);
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
     * invoke leanclound interface to upload local file by camera
     * @param filePath
     * @param file
     */
    public void uploadCamerImage(String filePath, File file) {
        try {
            final AVFile avfile = AVFile.withAbsoluteLocalPath("error-answer.jpg", filePath);
            avfile.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null)
                    Log.d("upload-net-url", avfile.getUrl());//返回一个唯一的 Url 地址
                }
            },new ProgressCallback() {
                @Override
                public void done(Integer integer) {
                    Log.d("upload",integer.toString());
                    // 上传进度数据，integer 介于 0 和 100。
                }
            });
        } catch (FileNotFoundException e) {
            Log.d("upload-exception", e.toString());
            e.printStackTrace();
        }
    }

    /**
     * get tamp iamge ,
     * @return File
     */
    public static File getTempImage() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File tempFile = new File(Environment.getExternalStorageDirectory(), "temp.jpg");

            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempFile;
        }
        return null;
    }


}
