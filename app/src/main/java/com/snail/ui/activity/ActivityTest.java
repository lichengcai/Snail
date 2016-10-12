package com.snail.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.snail.R;

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
            view.loadUrl(url);
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
                    Thread.sleep(2000);
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
