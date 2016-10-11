package com.snail.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snail.R;
import com.snail.news.listener.OnItemClickListener;
import com.snail.news.listener.OnItemLongClickListener;
import com.snail.news.model.News;
import com.snail.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/11.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<News> mData;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    public NewsListAdapter(Context context,ArrayList<News> mData) {
        this.mContext = context;
        this.mData = mData;
        for (int i=0; i<mData.size(); i++){
            Log.d("NewsListAdapter","mData---" + mData.get(i).toString());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        News news = mData.get(position);
        Log.d("onBindViewHolder","news toString---" + news.toString());
        if (holder instanceof NewsHolder) {
            Log.d("onBindViewHolder","holder instanceof NewsHolder");
            ((NewsHolder) holder).text_time.setText(news.getPtime());
            ((NewsHolder) holder).text_title.setText(news.getTitle());
            ImageLoader.getInstance().displayImage(mContext,news.getImgsrc(),((NewsHolder) holder).image);

            final int pos = holder.getLayoutPosition();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class NewsHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text_title;
        private TextView text_time;

        NewsHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_news_list);
            text_title = (TextView) itemView.findViewById(R.id.text_news_title);
            text_time = (TextView) itemView.findViewById(R.id.text_news_time);
        }
    }
}
