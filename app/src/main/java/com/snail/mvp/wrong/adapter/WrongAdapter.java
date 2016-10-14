package com.snail.mvp.wrong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snail.R;
import com.snail.mvp.wrong.model.WrongBean;
import com.snail.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/14.
 */

public class WrongAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<WrongBean> mData;

    public WrongAdapter(Context mContext,ArrayList<WrongBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WrongHolder(LayoutInflater.from(mContext).inflate(R.layout.item_wrong,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WrongHolder) {
            WrongBean wrongBean = mData.get(position);
            ((WrongHolder) holder).text_title.setText(wrongBean.getTitle());
            ((WrongHolder) holder).text_content.setText(wrongBean.getContent());
            Log.d("onBindViewHolder","imageUrl---"+ wrongBean.getImgUrl());
            ImageLoader.getInstance().displayImage(mContext,wrongBean.getImgUrl(),((WrongHolder) holder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class WrongHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView text_title;
        private TextView text_content;

        public WrongHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_wrong);
            text_title = (TextView) itemView.findViewById(R.id.text_title);
            text_content = (TextView) itemView.findViewById(R.id.text_content);
        }
    }
}
