package com.snail.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.snail.R;
import com.snail.adapter.NotesRecyclerAdapter;
import com.snail.bean.Notes;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
//        recycler_note = (RecyclerView) findViewById(R.id.recycler_note);
        initList();
        initListener();
        recycler_note.setLayoutManager(new LinearLayoutManager(this));
        NotesRecyclerAdapter notesRecyclerAdapter = new NotesRecyclerAdapter(this, list4notes);
        recycler_note.setAdapter(notesRecyclerAdapter);
    }

    private void initListener() {
        img_newNote.setOnClickListener(this);
    }

    private void initList() {
        Notes notes1 = new Notes("标题一", "2016/1/1", "第一个备忘录的内容");
        Notes notes2 = new Notes("标题二", "2016/2/1", "第二个备忘录的内容");
        Notes notes3 = new Notes("标题三", "2016/3/1", "第三个备忘录的内容");
        list4notes.add(notes1);
        list4notes.add(notes2);
        list4notes.add(notes3);
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
                        notes.setTitle(edt_title.getText().toString());
                        Intent intent = new Intent(NoteActivity.this,NotesEditActivity.class);
                        startActivity(intent);
                    }
                }).create();
                builder.show();
        }
    }
}
