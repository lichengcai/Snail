package com.snail.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.snail.R;
import com.snail.adapter.BookAdapter;
import com.snail.bean.Notes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookActivity extends ActivityBase {
    private RecyclerView recyclerBook;
    private ArrayList<Notes> listBooks = new ArrayList<>();

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        recyclerBook = (RecyclerView) findViewById(R.id.recycler_books);
        initList();
        recyclerBook.setLayoutManager(new LinearLayoutManager(this));
//        NotesRecyclerAdapter notesRecyclerAdapter = new NotesRecyclerAdapter(this, listBooks);
        BookAdapter bookAdapter = new BookAdapter(this, listBooks);
        recyclerBook.setAdapter(bookAdapter);
    }
    private void initList() {
        Notes notes1 = new Notes("标题一", "2016/1/1", "第一个备忘录的内容");
        Notes notes2 = new Notes("标题二", "2016/2/1", "第二个备忘录的内容");
        Notes notes3 = new Notes("标题三", "2016/3/1", "第三个备忘录的内容");
        listBooks.add(notes1);
        listBooks.add(notes2);
        listBooks.add(notes3);
        requestBooks();
    }

    private void requestBooks() {
        String reqUrl = "https://frodo.douban.com/jsonp/subject_collection/book_fiction/items?os=android&for_mobile=1&start=36&count=18&loc_id=0&_=" + new Date().getTime();
        final Request request=new Request.Builder()
                .get()
                .tag(this)
                .url(reqUrl)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String dataString = "";
                    if (response.isSuccessful()) {
                        dataString = response.body().string();

                        Gson gson = new Gson();
                        Map data = gson.fromJson(dataString, Map.class);
                        ArrayList arr = (ArrayList) data.get("subject_collection_items");


                        Log.i("map to string", data.toString());
                        Log.i("WY","打印GET响应的数据：" + dataString);
                        Log.d("response", dataString);
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    Log.d("response catch", "========");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
