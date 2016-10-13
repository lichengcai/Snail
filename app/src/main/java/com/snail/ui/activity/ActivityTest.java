package com.snail.ui.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.snail.R;
import com.snail.widget.flipshare.FlipShareView;
import com.snail.widget.flipshare.ShareItem;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chengcai on 2016/10/11.
 */

public class ActivityTest extends ActivityBase {
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.layout_loading)
    RelativeLayout mLayout_loading;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private TestHandler mHandler = new TestHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        String url_3w = getIntent().getStringExtra("url_3w");

        if (!TextUtils.isEmpty(url_3w)) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url_3w);
            mWebView.setWebViewClient(new HelloWebViewClient());
        }

        new Thread(new ThreadShow()).start();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlipShareView share = new FlipShareView.Builder(ActivityTest.this, mFab)
                        .addItem(new ShareItem("Facebook", Color.WHITE, 0xff43549C, BitmapFactory.decodeResource(getResources(), R.drawable.ic_share)))
                        .addItem(new ShareItem("Twitter", Color.WHITE, 0xff4999F0, BitmapFactory.decodeResource(getResources(), R.drawable.ic_share)))
                        .addItem(new ShareItem("Google+", Color.WHITE, 0xffD9392D, BitmapFactory.decodeResource(getResources(), R.drawable.ic_share)))
                        .addItem(new ShareItem("http://www.wangyuwei.me", Color.WHITE, 0xff57708A))
                        .setBackgroundColor(0x60000000)
                        .setItemDuration(500)
                        .setSeparateLineColor(0x30000000)
                        .setAnimType(FlipShareView.TYPE_SLIDE)
                        .create();
            }
        });

    }

    private static class TestHandler extends Handler {
        private WeakReference<ActivityTest> ref;
        private ActivityTest act;

        public TestHandler(ActivityTest test) {
            ref = new WeakReference<>(test);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (act.mLayout_loading != null)
                        act.mLayout_loading.setVisibility(View.GONE);
                    if (act.mFab != null)
                        act.mFab.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }else {
            finish();
        }
    }

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl("https://m.douban.com/book/subject/26840552/");
            return true;
        }
    }
    // 线程类
    class ThreadShow implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(3500);
                    Message msg = new Message();
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
