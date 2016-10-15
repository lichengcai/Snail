package com.snail.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.snail.R;
import com.snail.bean.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class NotesEditActivity extends ActivityBase{
    Intent intent;
    Bundle bundle;
    String title;
    String completeTime;
    String descption;
    EditText editText;
    TextView completeText;
    AVObject userAVObj = new AVObject("noteBean");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
        init();
    }

    private void init() {
        initView();
        initBindEvent();
        initBundle();
        title = getInputTitle();
    }

    private void initBindEvent() {
        completeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descption = editText.getText().toString();
                SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
                completeTime=dateformat1.format(new Date());
                Log.d("noteinput", "title " + title + "  descption:" + descption +" completeTime: "+ completeTime);
                setNoteData(title, descption, completeTime);
            }
        });
    }
    private void setNoteData(String title , String body, String completeTime) {
        userAVObj.put("title", title);
        userAVObj.put("body", body);
        userAVObj.put("createTime", completeTime);
        userAVObj.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Toast.makeText(NotesEditActivity.this,"备忘添加成功",Toast.LENGTH_SHORT).show();
                    NotesEditActivity.this.finish();
                }
                else {
                    int code = e.getCode();
                    if (code == 202) {
                        // 更多错误码处理 https://leancloud.cn/docs/error_code.html#_202
                        Toast.makeText(NotesEditActivity.this,"erro....",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edt_noteBody);
        completeText = (TextView) findViewById(R.id.tv_commit);
    }


    private void initBundle() {
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
    }

    public String getInputTitle() {
        return bundle.getString("title");
    }
}
