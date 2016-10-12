package com.snail.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.snail.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chengcai on 2016/10/11.
 */

public class ActivityTest extends ActivityBase {
    @BindView(R.id.webView)
    WebView mWebView;
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

    }

//    @Override
//    //设置回退
//    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack(); //goBack()表示返回WebView的上一页面
//            return true;
//        }
//        finish();//结束退出程序
//        return false;
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finish();
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
}
