package com.snail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snail.R;
import com.snail.bean.Answer;
import com.snail.mvp.news.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by zhangshibiao on 16/10/30.
 */

public class AnswerAdapter extends RecyclerView.Adapter { private Context context;
    private ArrayList<Answer> list;

    public AnswerAdapter(Context context, ArrayList<Answer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new AnswerAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_answer,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AnswerAdapter.MyViewHolder) {
            Log.d("qa_desc",list.get(position).toString());
            ((MyViewHolder) holder).qa_desc.setText(list.get(position).getDesc());
            ((MyViewHolder) holder).qa_time.setText(list.get(position).getCreateTime());
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  qa_desc, qa_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            qa_time = (TextView) itemView.findViewById(R.id.qa_time);
            qa_desc = (TextView) itemView.findViewById(R.id.qa_answer);
        }
    }
}
