package com.snail.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snail.R;
import com.snail.adapter.NotesRecyclerAdapter;
import com.snail.bean.Notes;

import java.util.ArrayList;

public class NewsActivity extends ActivityBase {
    private RecyclerView recyclerBook;
    private ArrayList<Notes> listBooks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerBook = (RecyclerView) findViewById(R.id.recycler_books);
        initList();
        recyclerBook.setLayoutManager(new LinearLayoutManager(this));
        NotesRecyclerAdapter notesRecyclerAdapter = new NotesRecyclerAdapter(this, listBooks);
        recyclerBook.setAdapter(notesRecyclerAdapter);
    }

    private void initList() {
        Notes notes1 = new Notes("标题一", "2016/1/1", "第一个备忘录的内容");
        Notes notes2 = new Notes("标题二", "2016/2/1", "第二个备忘录的内容");
        Notes notes3 = new Notes("标题三", "2016/3/1", "第三个备忘录的内容");
        listBooks.add(notes1);
        listBooks.add(notes2);
        listBooks.add(notes3);
    }
}
