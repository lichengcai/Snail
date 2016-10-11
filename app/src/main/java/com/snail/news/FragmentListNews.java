package com.snail.news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.snail.R;
import com.snail.news.adapter.NewsListAdapter;
import com.snail.news.listener.OnItemClickListener;
import com.snail.news.listener.OnItemLongClickListener;
import com.snail.news.model.News;
import com.snail.news.model.NewsModelImpl;
import com.snail.news.presenter.NewsListPresenter;
import com.snail.news.presenter.NewsListPresenterImpl;
import com.snail.news.view.NewsListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chengcai on 2016/10/10.
 */

public class FragmentListNews extends Fragment implements NewsListView{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private NewsListPresenter mPresenter;
    private NewsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int MSG_GET_NEWS_INFO = 0;
    private int mType = ActivityNews.NEWS_EDUCATION;
    private int pageIndex = 0;

    private NewsHandler mHandler = new NewsHandler(this);
    public static class NewsHandler extends Handler {
        private WeakReference<FragmentListNews> ref;
        private FragmentListNews frg;

        NewsHandler(FragmentListNews f) {
            ref = new WeakReference<FragmentListNews>(f);
            frg = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_NEWS_INFO:
                    Log.d("handleMessage","MSG_GET_NEWS_INFO");
                    frg.mRecyclerView.setAdapter(frg.mAdapter);
                    frg.mAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.d("handleMessage","onItemClick---" + position);
                            Toast.makeText(frg.getActivity(),"onItemClick---" + position,Toast.LENGTH_SHORT).show();
                        }
                    });

                    frg.mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
                        @Override
                        public void onItemLongClick(View view, int position) {
                            Log.d("handleMessage","onItemLongClick---" + position);
                            Toast.makeText(frg.getActivity(),"onItemLongClick---" + position,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
    }

    public static FragmentListNews newInstance(int type) {
        FragmentListNews fragmentListNews = new FragmentListNews();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragmentListNews.setArguments(bundle);
        return fragmentListNews;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        ButterKnife.bind(this,view);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
        init();
        return view;
    }

    private void init() {
        mPresenter = new NewsListPresenterImpl(this);
        mPresenter.setNewsList(mType,pageIndex,false,false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public void setNews(ArrayList<News> data) {
        mAdapter = new NewsListAdapter(getActivity(),data);
        mHandler.sendEmptyMessage(MSG_GET_NEWS_INFO);
    }

    @Override
    public void setFail() {

    }
}
