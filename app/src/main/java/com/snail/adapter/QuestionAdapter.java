package com.snail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.R;
import com.snail.bean.Notes;
import com.snail.bean.Question;
import com.snail.mvp.news.listener.OnItemClickListener;
import com.snail.mvp.news.listener.OnItemLongClickListener;
import com.snail.ui.activity.NotesEditActivity;

import java.util.ArrayList;

/**
 * Created by zhangshibiao on 16/10/30.
 */

public class QuestionAdapter extends RecyclerView.Adapter { private Context context;
    private ArrayList<Question> list;
    private NotesRecyclerAdapter.MyViewHolder myViewHolder;
    private OnItemClickListener mOnItemClickListener;

    public QuestionAdapter(Context context,ArrayList<Question> list) {
        this.context = context;
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new QuestionAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question,parent,false));
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof QuestionAdapter.MyViewHolder) {
            ((MyViewHolder) holder).qa_tag.setText(list.get(position).getTag());
            ((MyViewHolder) holder).qa_desc.setText(list.get(position).getDesc());
            ((MyViewHolder) holder).qa_time.setText(list.get(position).getCreateTime());

            ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOnItemClickListener.onItemClick(((MyViewHolder) holder).itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView qa_tag, qa_desc, qa_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            qa_tag = (TextView) itemView.findViewById(R.id.qa_tag);
            qa_time = (TextView) itemView.findViewById(R.id.qa_time);
            qa_desc = (TextView) itemView.findViewById(R.id.qa_desc);
        }
    }
}
