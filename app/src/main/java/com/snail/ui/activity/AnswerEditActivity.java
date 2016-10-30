package com.snail.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.snail.R;

public class AnswerEditActivity extends AppCompatActivity {

    EditText qaDesc;
    Button commitBtn;
    String parentId,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_edit);
        init();
    }

    private void init() {
        initView();
        initData();
        bindEvent();
    }

    private void initData() {
        Intent intent = getIntent();
        parentId = intent.getStringExtra("questionId");
    }

    private void bindEvent() {
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitAnswer();
            }
        });
    }

    private void commitAnswer() {
        AVObject answer = new AVObject("Answer");
        String desc = qaDesc.getText().toString();
        answer.put("desc",desc);
        answer.put("parentId",parentId);
        answer.put("creatTime","2016/10/31");
        Log.d("saveanswer","answer" + answer.toString());
        answer.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                Log.d("saveanswer","saveanswer.saveInBackground");
                if(e == null){
                    Log.d("saveanswer","success!");
                    Toast.makeText(AnswerEditActivity.this,"saveanswer success",Toast.LENGTH_SHORT).show();
                    AnswerEditActivity.this.finish();
                }
                else {
                    Log.d("saveUser",e.toString());
                    int code = e.getCode();
                    Toast.makeText(AnswerEditActivity.this,"error code." + code,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initView() {
        qaDesc = (EditText) findViewById(R.id.qa_desc);
        commitBtn = (Button) findViewById(R.id.commit_btn);
    }
}
