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
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Book> list;
    private MyViewHolder myViewHolder;
    private Handler handler;
    public BookAdapter(Context context, ArrayList<Book> list) {
        this.context = context;
        this.list = list;
        initHandler();

    }
    private  void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                myViewHolder.cover.setImageBitmap((Bitmap) msg.obj);
            }
        };
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R
                .layout.item_books, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        myViewHolder.tv_title.setText(list.get(position).getTitle());
        final String url = list.get(position).getCover();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一个url对象
                URL urlobj= null;
                try {
                    urlobj = new URL(url);
                    //打开URL对应的资源输入流
                    InputStream is= urlobj.openStream();
                    //从InputStream流中解析出图片
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Message msg = new Message();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                    //  imageview.setImageBitmap(bitmap);
                    //发送消息，通知UI组件显示图片
                    //关闭输入流
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
//        myViewHolder.cover.setText(list.get(position).getCover());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView cover;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            cover = (ImageView) itemView.findViewById(R.id.cover);
        }
    }
}
