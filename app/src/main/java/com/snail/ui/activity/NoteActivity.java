package com.snail.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snail.R;
import com.snail.adapter.NotesRecyclerAdapter;
import com.snail.bean.Notes;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class NoteActivity extends ActivityBase {
//    @BindView(R.id.recycler_note)
    private RecyclerView recycler_note;
    private ArrayList<Notes> list4notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        recycler_note = (RecyclerView) findViewById(R.id.recycler_note);
        initList();
        recycler_note.setLayoutManager(new LinearLayoutManager(this));
        NotesRecyclerAdapter notesRecyclerAdapter = new NotesRecyclerAdapter(this, list4notes);
        recycler_note.setAdapter(notesRecyclerAdapter);
    }

    private void initList() {
        Notes notes1 = new Notes("标题一", "2016/1/1", "第一个备忘录的内容");
        Notes notes2 = new Notes("标题二", "2016/2/1", "第二个备忘录的内容");
        Notes notes3 = new Notes("标题三", "2016/3/1", "第三个备忘录的内容");
        list4notes.add(notes1);
        list4notes.add(notes2);
        list4notes.add(notes3);
    }
}
