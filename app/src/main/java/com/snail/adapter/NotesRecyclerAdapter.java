package com.snail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.snail.R;
import com.snail.bean.Notes;
import com.snail.mvp.news.listener.OnItemClickListener;
import com.snail.mvp.news.listener.OnItemLongClickListener;

import java.util.ArrayList;

/**
 * Created by chenzhiwei on 16/10/9.
 */
public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Notes> list;
    private MyViewHolder myViewHolder;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;
    public NotesRecyclerAdapter(Context context, ArrayList<Notes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R
                .layout.item_notes, parent, false));
        return myViewHolder;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        myViewHolder.tv_title.setText(list.get(position).getTitle());
        myViewHolder.tv_time.setText(list.get(position).getCreateTime());
        myViewHolder.tv_body.setText(list.get(position).getBody());
        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            String objId = list.get(position).getId();
            @Override
            public boolean onLongClick(View v) {
                mOnItemLongClickListener.onItemLongClick(myViewHolder.itemView,position);
                return false;
            }
        });
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(myViewHolder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_time, tv_body;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_body = (TextView) itemView.findViewById(R.id.tv_body);
        }
    }
}
