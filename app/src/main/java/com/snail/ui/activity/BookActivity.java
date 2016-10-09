package com.snail.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.snail.R;
import com.snail.bean.Notes;

import java.util.ArrayList;

public class BookActivity extends ActivityBase {
    private RecyclerView recyclerBook;
    private ArrayList<Notes> listBooks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        recyclerBook = (RecyclerView) findViewById(R.id.recycler_books);
    }
}
