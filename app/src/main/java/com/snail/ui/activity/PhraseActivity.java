package com.snail.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snail.R;
import com.snail.adapter.PhraseAdapter;
import com.snail.bean.Phrase;
import com.snail.mvp.dictionary.ActivityChDictionary;
import com.snail.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Phaser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chengcai on 2016/10/30.
 */

public class PhraseActivity extends ActivityBase {
    @BindView(R.id.list_phrase)
    RecyclerView mRecycler;
    @BindView(R.id.etSearch)
    EditText mSearch;
    @BindView(R.id.btn_search)
    Button mBtn_search;
    private String mKey;
    private PhraseAdapter mAdapter;

    private String url = "http://api.avatardata.cn/ChengYu/Search?key=c2a69e2219cc4ac996f61197b5ea1376&keyWord=";
    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ArrayList<Phrase> array = (ArrayList<Phrase>) msg.obj;
                    mAdapter = new PhraseAdapter(PhraseActivity.this,array);
                    mRecycler.setLayoutManager(new LinearLayoutManager(PhraseActivity.this));
                    mRecycler.setAdapter(mAdapter
                    );
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);
        ButterKnife.bind(this);

        mBtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        getPhrase();

            }
        });
    }

    private void getPhrase() {
        if (TextUtils.isEmpty(mSearch.getText().toString())) {
            Toast.makeText(PhraseActivity.this,"请输入要查询的汉字",Toast.LENGTH_SHORT).show();
        }else {
            mKey = mSearch.getText().toString();
        }
        HttpUtils.get(url + mKey, new HttpUtils.LoadCallback() {
            @Override
            public void success(String json) {
                Log.d("getPhrase","json---" + json);
                ArrayList<Phrase> array = Phrase.getPhrase(json);
                mHander.obtainMessage(0,array).sendToTarget();

            }

            @Override
            public void fail(IOException e) {

            }
        });
    }
}
