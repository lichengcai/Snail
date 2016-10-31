package com.snail.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.snail.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuestionEditActivity extends ActivityBase {

    EditText tagView, descView;
    Button commitBtn;
    ImageView backBtn;
    AVObject question;
    String tag, desc,creatTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_edit);
        initView();
        initData();
    }

    private void initData() {
        question = new AVObject("Question");
    }

    private void initView() {
        tagView = (EditText) findViewById(R.id.qa_tag);
        descView = (EditText) findViewById(R.id.qa_desc);
        commitBtn = (Button) findViewById(R.id.qa_commit);
        backBtn = (ImageView) findViewById(R.id.img_back);
        bindEvent();
    }

    private void bindEvent() {
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestion();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionEditActivity.this.finish();
            }
        });
//        saveQuesion();
    }

    private void setQuestion() {
        tag = tagView.getText().toString();
        desc = descView.getText().toString();
        if ("".equals(tag) || "".equals(desc)) {
            Toast.makeText(this, "内容不能为空",Toast.LENGTH_SHORT).show();
            return ;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        creatTime = formatter.format(date).toString();
        question.put("creatTime", creatTime);
        question.put("tag", tag);
        question.put("desc", desc);
        saveQuesion();
    }




    private void saveQuesion() {
        question.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                Log.d("savequestion","question.saveInBackground");
                if(e == null){
                    Log.d("question","success!");
                    Toast.makeText(QuestionEditActivity.this,"savequestion success",Toast.LENGTH_SHORT).show();
                    QuestionEditActivity.this.finish();
                }
                else {
                    Log.d("saveUser",e.toString());
                    int code = e.getCode();
                    Toast.makeText(QuestionEditActivity.this,"save question erro." + code,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
