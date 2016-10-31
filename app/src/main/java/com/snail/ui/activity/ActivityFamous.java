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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.R;
import com.snail.adapter.FamousAdapter;
import com.snail.bean.Famous;
import com.snail.bean.Phrase;
import com.snail.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/10/31.
 */

public class ActivityFamous extends ActivityBase {
    @BindView(R.id.text_title)
    TextView mTextTitle;
    @BindView(R.id.etSearch)
    EditText mSearch;
    @BindView(R.id.btn_search)
    Button mBtn;
    @BindView(R.id.list_phrase)
    RecyclerView mRecyclerView;
    @BindView(R.id.linear_loading)
    RelativeLayout mLoading;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    ArrayList<Famous> arrayList = (ArrayList<Famous>) msg.obj;
                    if (mLoading != null)
                        mLoading.setVisibility(View.GONE);

                    if (arrayList != null) {
                        mAdapter = new FamousAdapter(ActivityFamous.this,arrayList);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(ActivityFamous.this));
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    break;
            }
        }
    };

    private String mKey;
    private FamousAdapter mAdapter;

    private String url = "http://api.avatardata.cn/MingRenMingYan/LookUp?key=19d23345f8384d18a89db6005d603e2a&page=1&rows=20&keyword=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);
        ButterKnife.bind(this);

        mTextTitle.setText("名人名言查询");
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFamous();
                if (mLoading != null)
                    mLoading.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getFamous() {
        if (TextUtils.isEmpty(mSearch.getText().toString())) {
            Toast.makeText(ActivityFamous.this,"请输入要查询的汉字",Toast.LENGTH_SHORT).show();
        }else {
            mKey = mSearch.getText().toString();
        }
        HttpUtils.get(url + mKey, new HttpUtils.LoadCallback() {
            @Override
            public void success(String json) {
                Log.d("getPhrase","json---" + json);
                ArrayList<Famous> array = Famous.getFamous(json);
                mHandler.obtainMessage(0,array).sendToTarget();

            }

            @Override
            public void fail(IOException e) {

            }
        });
    }
}
