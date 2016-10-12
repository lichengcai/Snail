package com.snail.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.feedback.ThreadActivity;
import com.google.gson.Gson;
import com.snail.R;
import com.snail.bean.Book;
import com.snail.bean.Notes;
import com.snail.ui.activity.BookActivity;
import com.snail.utils.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class BookAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Book> list;
    private MyViewHolder myViewHolder;
    public BookAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R
                .layout.item_books, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Book book = list.get(position);
            ((MyViewHolder) holder).tv_book_title.setText(book.getTitle());
            ((MyViewHolder) holder).tv_book_info.setText(book.getInfo());
            ImageLoader.getInstance().displayImage(context,book.getCover(),((MyViewHolder) holder).cover);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_book_title;
        TextView tv_book_info;
        ImageView cover;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_book_title = (TextView) itemView.findViewById(R.id.text_book_title);
            tv_book_info = (TextView) itemView.findViewById(R.id.text_book_info);
            cover = (ImageView) itemView.findViewById(R.id.img_book_cover);
        }
    }
}
