package com.snail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snail.R;
import com.snail.bean.Famous;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/31.
 */

public class FamousAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Famous> arrayList;
    public FamousAdapter(Context context,ArrayList<Famous> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FamousHolder(LayoutInflater.from(context).inflate(R.layout.item_phrase,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FamousHolder) {
            ((FamousHolder) holder).name.setText(arrayList.get(position).getFamous_name()+ ": ");
            ((FamousHolder) holder).saying.setText(arrayList.get(position).getFamous_saying());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private class FamousHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView saying;

        public FamousHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_name);
            saying = (TextView) itemView.findViewById(R.id.text_phrase);
        }
    }
}
