package com.snail.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.okhttp.Response;
import com.google.gson.Gson;
import com.snail.R;
import com.snail.adapter.BookAdapter;
import com.snail.adapter.WordAdapter;
import com.snail.bean.Book;
import com.snail.bean.Notes;
import com.snail.bean.Word;
import com.snail.common.Urls;
import com.snail.mvp.news.listener.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class WordsActivity extends ActivityBase {

    RecyclerView recyclerView;
    Handler handler;
    final String REQURL = "https://route.showapi.com/8-10?class_id=45090&course=5&showapi_appid=25634&showapi_sign=774a92db163f11e78e1f595b75cdb278";
    private final OkHttpClient client = new OkHttpClient();
    private  ArrayList<Word> wordList;
    LinearLayoutManager layoutManager;
    WordAdapter wordAdapter;
    AVQuery query = null;
    int pageIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        init();
    }

    private void init() {
        query = new AVQuery<>("WordBean");
        wordList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        initElement();
        bindEvent();
        initHandler();
        getWordListFromLeanClound();
    }

    private void bindEvent() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (wordAdapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == wordAdapter.getItemCount() && wordAdapter.isShowFooter()) {
                        pageIndex ++;
                        WordsActivity.this.getWordListFromLeanClound();
//                        mPresenter.setNewsList(mType,pageIndex ,false,true);
                        Log.d("addAllListener","onScrollStateChanged");
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void getWordListFromLeanClound() {
        int pageIndex = this.pageIndex;
        query.limit(10);
        Log.d("wordstring","============" + pageIndex) ;
        query.skip(10 * pageIndex);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {

                Word word = null;
                for( AVObject avobj :list) {
                    word = new Word();
                    word.setName(avobj.get("name").toString());
                    word.setDesc(avobj.get("desc").toString());
                    word.setSymbol(avobj.get("symbol").toString());
                    word.setSound(avobj.get("sound").toString());
                    Log.d("wordstring",word.toString());
                    wordList.add(word);
                }
                handler.sendEmptyMessage(0);
            }
        });
    }

    /**
     * 暂时废弃
     * 妈蛋这个接口调用了几次就不让用了, 又注册一个账号,然后数据导入到leancloud 库中
     * 之后用leancloud 写个server,每天固定抓取导入
     */
    private void getWordList() {
        String reqUrl = REQURL + "&showapi_timestamp=" + new Date().getTime();
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(reqUrl)
                .build();
        new Thread() {
            @Override
            public void run() {
                okhttp3.Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String dataString  = "";
                    if (response.isSuccessful()) {
                        dataString = response.body().string();
                        Log.d("reqDataString", dataString);
                        Gson gson = new Gson();
                        Map resultMap = gson.fromJson(dataString, Map.class);
                        Map resBody = (Map) resultMap.get("showapi_res_body");
                        ArrayList<Map> list = (ArrayList<Map>) resBody.get("list");
                        Word word = null;
                        for(Map obj:list) {
                            word = new Word();
                            word.setName(obj.get("name").toString());
                            word.setDesc(obj.get("desc").toString());
                            word.setSymbol(obj.get("symbol").toString());
                            word.setSound(obj.get("sound").toString());
                            wordList.add(word);
                        }
                        handler.sendEmptyMessage(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    if (wordAdapter == null) {
                        wordAdapter = new WordAdapter(WordsActivity.this, wordList);
                        recyclerView.setAdapter(wordAdapter);
                    }
                    else {
                        wordAdapter.notifyDataSetChanged();
                    }

                }
            }
        };
    }

    private void initElement() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_word);
        recyclerView.setLayoutManager(layoutManager);
    }
}
