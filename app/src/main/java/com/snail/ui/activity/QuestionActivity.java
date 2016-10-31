package com.snail.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.QuoteSpan;
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
import com.snail.adapter.QuestionAdapter;
import com.snail.bean.Answer;
import com.snail.bean.Question;
import com.snail.mvp.news.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends ActivityBase {


    private TextView addQa;
    private ImageView backImg;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    QuestionAdapter questionAdapter;
    Handler handler;
    AVQuery query = null;
    ArrayList<Question> questionList = new ArrayList<>();

     int pageIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        init();
    }

    private void init() {
        layoutManager = new LinearLayoutManager(this);
        query = new AVQuery<>("Question");
        initView();
        initBindEvent();
        initHandler();
        requestData();
    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    if (questionAdapter == null) {
                        questionAdapter = new QuestionAdapter(QuestionActivity.this, questionList);
                                questionAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("tag", questionList.get(position).getTag());
                                        bundle.putString("time", questionList.get(position).getCreateTime());
                                        bundle.putString("desc", questionList.get(position).getDesc());
                                        bundle.putString("id", questionList.get(position).getId());
                                        intent.putExtra("bundle", bundle);
                                        QuestionActivity.this.startActivity(intent);
                                        Log.d("question",questionList.get(position).toString());
                                        Toast.makeText(QuestionActivity.this,"aaaaa",Toast.LENGTH_SHORT).show();
                                    }
                        });
                        recyclerView.setAdapter(questionAdapter);
                    }
                    else {
                        questionAdapter.notifyDataSetChanged();
                    }

                }
            }
        };
    }

    private void requestData() {
        int pageIndex = this.pageIndex;
//        query.limit(10);
//        query.skip(10 * pageIndex);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                Question quesion = null;
                for( AVObject avobj :list) {
                    quesion = new Question();
                    quesion.setTag(avobj.get("tag").toString());
                    quesion.setDesc(avobj.get("desc").toString());
                    quesion.setId(avobj.getObjectId());
                    quesion.setCreateTime(avobj.get("creatTime").toString());
                    Log.d("quesionstring",quesion.toString());
                    questionList.add(quesion);
                }
                handler.sendEmptyMessage(0);
            }
        });
    }

    private void initBindEvent() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionActivity.this.finish();
            }
        });
        addQa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, QuestionEditActivity.class);
                QuestionActivity.this.startActivity(intent);
            }
        });
    }

    private void initView() {
        backImg = (ImageView) findViewById(R.id.img_back);
        addQa = (TextView) findViewById(R.id.add_qa);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_questions);
        recyclerView.setLayoutManager(layoutManager);
    }


}
