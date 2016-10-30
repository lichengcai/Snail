package com.snail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snail.R;
import com.snail.bean.Word;
import com.snail.utils.Player;

import java.util.ArrayList;

/**
 * Created by zhangshibiao on 16/10/15.
 */

public class WordAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<Word> list;
    Boolean mShowFooter = true;
    Player player = new Player();
    public WordAdapter(Context context, ArrayList<Word> list) {
        this.context = context;
        this.list = list;
    }
    public void isShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    public boolean isShowFooter() {
        return mShowFooter;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("listSize",list.size() + "");
        return  new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_word,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).word_name.setText(list.get(position).getName());
            ((MyViewHolder) holder).word_desc.setText(list.get(position).getDesc());
            ((MyViewHolder) holder).word_sound.setText(list.get(position).getSound());
            ((MyViewHolder) holder).word_symbol.setText(list.get(position).getSymbol());
            final String playUrl = list.get(position).getSound();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.playUrl(playUrl);
                }
            });
            Log.d("wordData",list.get(position).getName());
        }
    }


    @Override
    public int getItemCount() {
        return  list.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView word_name, word_desc, word_symbol, word_sound;
        ImageView word_voice;

        public MyViewHolder(View itemView) {
            super(itemView);
            word_name = (TextView) itemView.findViewById(R.id.word_name);
            word_desc = (TextView) itemView.findViewById(R.id.word_desc);
            word_symbol = (TextView) itemView.findViewById(R.id.word_symbol);
            word_sound = (TextView) itemView.findViewById(R.id.word_sound);
            word_voice = (ImageView) itemView.findViewById(R.id.word_voice);
        }
    }
}
