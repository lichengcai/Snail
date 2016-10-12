package com.snail.news.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.snail.R;
import com.snail.news.FooterHolder;
import com.snail.news.listener.OnItemClickListener;
import com.snail.news.listener.OnItemLongClickListener;
import com.snail.news.model.News;
import com.snail.utils.ImageLoader;
import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/11.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM =0;
    private static final int TYPE_FOOTER = 1;
    private Context mContext;
    private ArrayList<News> mData;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    private boolean mShowFooter = true;
    private int mCurrentLastItem = -1;

    public String getUrl_3w(int position) {
        return mData.get(position).getUrl_3w();
    }
    public void loadMore(ArrayList<News> arrayList) {
        if (arrayList != null)
            mData.addAll(arrayList);
    }
    public void isShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    public boolean isShowFooter() {
        return mShowFooter;
    }

    public NewsListAdapter(Context context,ArrayList<News> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_footer,parent,false));
        }else {
            return new NewsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NewsHolder) {
            if (position > mCurrentLastItem) {
                Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.item_slide_in_bottom);
                holder.itemView.setAnimation(animation);
                mCurrentLastItem = position;
            }

            News news = mData.get(position);
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
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getAnimation() != null && holder.itemView.getAnimation().hasStarted()) {
            holder.itemView.clearAnimation();
        }
    }

    @Override
    public int getItemCount() {
        int count = mShowFooter ? 1 : 0;
        return mData.size() + count;
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

    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
    }
}
