package com.snail.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.snail.R;
import com.snail.adapter.BookAdapter;
import com.snail.adapter.NotesRecyclerAdapter;
import com.snail.bean.Book;
import com.snail.bean.Notes;
import com.snail.mvp.news.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class NoteActivity extends ActivityBase implements View.OnClickListener {
    @BindView(R.id.recycler_note)
    RecyclerView recycler_note;

    @BindView(R.id.img_newNote)
    TextView img_newNote;
    private ArrayList<Notes> list4notes = new ArrayList<>();
    AVQuery<AVObject> query;
    Handler hanler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        initHandler();
        query = new AVQuery<>("noteBean");
//        recycler_note = (RecyclerView) findViewById(R.id.recycler_note);
        initList();
        initListener();
        recycler_note.setLayoutManager(new LinearLayoutManager(this));
        getNoteList();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            list4notes.clear();
            getNoteList();
        }

    }

    private void initHandler() {
        hanler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    NotesRecyclerAdapter notesRecyclerAdapter = new NotesRecyclerAdapter(NoteActivity.this, list4notes);
                    Log.d("requestsuccess",list4notes.toString());
                    recycler_note.setAdapter(notesRecyclerAdapter);
                }
            }
        };
    }

    private void initListener() {
        img_newNote.setOnClickListener(this);
    }
    private void getNoteList() {
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
//                List<AVObject> list4notes = list;
                ArrayList<Notes> notes = new ArrayList<Notes>();
                AVObject avobj ;
                for (int i=0; i<list.size(); i++) {
                    avobj = list.get(i);
                    String title = avobj.get("title").toString();
                    String createTime = avobj.get("createTime").toString();
                    String body = avobj.get("body").toString();
                    Notes note = new Notes(title, createTime, body);
                    list4notes.add(note);
                }
                Log.d("getdata",list4notes.toString());
                hanler.sendEmptyMessage(0);
            }
        });
    }
    private void initList() {
//        Notes notes1 = new Notes("标题一", "2016/1/1", "第一个备忘录的内容");
//        Notes notes2 = new Notes("标题二", "2016/2/1", "第二个备忘录的内容");
//        Notes notes3 = new Notes("标题三", "2016/3/1", "第三个备忘录的内容");
//        list4notes.add(notes1);
//        list4notes.add(notes2);
//        list4notes.add(notes3);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(final View v) {
        final Notes notes = new Notes();
        switch (v.getId()){
            case R.id.img_newNote:
                View view = LayoutInflater.from(NoteActivity.this).inflate(R.layout.dialog_title_edit,null);
                final EditText edt_title = (EditText) view.findViewById(R.id.edt_title);
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = edt_title.getText().toString();
                        notes.setTitle(title);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        Intent intent = new Intent(NoteActivity.this,NotesEditActivity.class);

                        intent.putExtra("bundle", bundle);
                        startActivityForResult(intent, 1000);
//                        startActivity(intent);
                    }
                }).create();
                builder.show();
        }
    }
}
