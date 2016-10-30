package com.snail.adapter;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snail.R;
import com.snail.bean.Phrase;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by chengcai on 2016/10/30.
 */

public class PhraseAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Phrase> mData;

    public PhraseAdapter(Context context,ArrayList<Phrase> mData) {
        this.context =context;
        this.mData =mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhraseHolder(LayoutInflater.from(context).inflate(R.layout.item_phrase,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PhraseHolder) {
            ((PhraseHolder) holder).textView.setText(mData.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class PhraseHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PhraseHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_phrase);
        }
    }

}
