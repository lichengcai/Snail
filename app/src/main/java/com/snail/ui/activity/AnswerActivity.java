package com.snail.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.snail.R;
import com.snail.adapter.AnswerAdapter;
import com.snail.adapter.QuestionAdapter;
import com.snail.bean.Answer;
import com.snail.bean.Question;
import com.snail.mvp.news.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AnswerActivity extends ActivityBase {

    String id, desc,creatTime,tag;
    Intent intent;
    Bundle bundle;
    TextView qaDesc,addAns;
    ImageView backBtn;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Handler handler;
    AnswerAdapter answerAdapter;
    ArrayList<Answer> answerList = new ArrayList<Answer>();
    int pageIndex = 1;
    AVQuery query = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        init();
    }

    private void init() {
        layoutManager = new LinearLayoutManager(this);
        query = new AVQuery<>("Answer");
        initHandler();
        initView();
        initBundle();
        setBaseData();
        setBaseViewData();
        initBindEvent();
        requesData();
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    if (answerAdapter == null) {
                        answerAdapter = new AnswerAdapter(AnswerActivity.this, answerList);
                        recyclerView.setAdapter(answerAdapter);
                    }
                    else {
                        answerAdapter.notifyDataSetChanged();
                    }

                }
            }
        };
    }

    private void initBindEvent() {
        addAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnswerActivity.this, AnswerEditActivity.class);
                intent.putExtra("questionId",id);
                AnswerActivity.this.startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerActivity.this.finish();
            }
        });
    }

    private void requesData() {
        int pageIndex = this.pageIndex;
        query.whereEqualTo("parentId", id);
        Log.d("parentId", id);
//        query.limit(10);
//        query.skip(10 * pageIndex);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                Answer answer = null;
                for( AVObject avobj :list) {
                    answer = new Answer();
                    answer.setParentId(avobj.get("parentId").toString());
                    answer.setCreateTime(avobj.get("creatTime").toString());
                    answer.setDesc(avobj.get("desc").toString());
                    Log.d("answerstring",answer.toString());
                    answerList.add(answer);
                }
                handler.sendEmptyMessage(0);
            }
        });

    }

    private void setBaseViewData() {
        qaDesc.setText(desc);
    }

    private void initView() {
        qaDesc = (TextView) findViewById(R.id.qa_desc);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_answers);
        recyclerView.setLayoutManager(layoutManager);
        addAns = (TextView) findViewById(R.id.add_ans);
        backBtn = (ImageView) findViewById(R.id.img_back);
        initBindEvent();
    }

    private void setBaseData() {
        id = bundle.getString("id");
        tag = bundle.getString("tag");
        creatTime  = bundle.getString("creatTime");
        desc = bundle.getString("desc");

    }

    private void initBundle() {
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");

    }
}
