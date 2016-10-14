package com.snail.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.snail.R;
import com.snail.adapter.BookAdapter;
import com.snail.bean.Book;
import com.snail.mvp.news.listener.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookActivity extends ActivityBase {
    private RecyclerView recyclerBook;
    private ArrayList<Book> listBooks = new ArrayList<>();
    private Handler handler;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        recyclerBook = (RecyclerView) findViewById(R.id.recycler_books);
        initHandler();
        requestBooks();
//        initList();
        recyclerBook.setLayoutManager(new LinearLayoutManager(this));
//        NotesRecyclerAdapter notesRecyclerAdapter = new NotesRecyclerAdapter(this, listBooks);


    }


    private  void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    final BookAdapter bookAdapter = new BookAdapter(BookActivity.this, listBooks);
                    bookAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(BookActivity.this,ActivityTest.class);
                            Book book = bookAdapter.getBook(position);
                            intent.putExtra("url_3w",book.getUrl());
                            startActivity(intent);

                        }
                    });
                    recyclerBook.setAdapter(bookAdapter);
                }
            }
        };
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
                        ArrayList<Map> arr = (ArrayList) data.get("subject_collection_items");
                        for (Map obj: arr
                             ) {
                            Map cover = (Map) obj.get("cover");

                            listBooks.add( new Book(obj.get("title").toString(), cover.get("url").toString(),obj.get("info").toString(),obj.get("url").toString()));
                        }

                        Log.i("map to string", data.toString());
                        Log.i("WY","打印GET响应的数据：" + dataString);
                        handler.sendEmptyMessage(0);
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
