package com.snail.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.snail.R;
import com.snail.common.Urls;
import com.snail.news.adapter.NewsListAdapter;
import com.snail.news.listener.OnItemClickListener;
import com.snail.news.listener.OnItemLongClickListener;
import com.snail.news.model.News;
import com.snail.news.model.NewsModelImpl;
import com.snail.news.presenter.NewsListPresenter;
import com.snail.news.presenter.NewsListPresenterImpl;
import com.snail.news.view.NewsListView;
import com.snail.ui.activity.ActivityTest;

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
    @BindView(R.id.layout_loading)
    RelativeLayout mLayoutLoading;
    @BindView(R.id.layout_fail)
    RelativeLayout mLayoutFail;

    private NewsListPresenter mPresenter;
    private NewsListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private static final int MSG_GET_NEWS_INFO = 0;
    private static final int MSG_GET_NEWS_EMPTY =1;
    private static final int MSG_GET_NEWS_MORE = 2;
    private int mType = ActivityNews.NEWS_EDUCATION;
    private int pageIndex = 0;

    private NewsHandler mHandler = new NewsHandler(this);
    public static class NewsHandler extends Handler {
        private WeakReference<FragmentListNews> ref;
        private FragmentListNews frg;

        NewsHandler(FragmentListNews f) {
            ref = new WeakReference<>(f);
            frg = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_NEWS_INFO:
                    if (frg.mSwipeRefreshLayout != null && frg.mSwipeRefreshLayout.isRefreshing()) {
                        frg.mSwipeRefreshLayout.setRefreshing(false);
                    }
                    if (frg.mLayoutLoading != null)
                        frg.mLayoutLoading.setVisibility(View.GONE);
                    if (frg.mLayoutFail != null)
                        frg.mLayoutFail.setVisibility(View.GONE);

                    frg.mRecyclerView.setAdapter(frg.mAdapter);
                    frg.mAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            String url_3w = frg.mAdapter.getUrl_3w(position);
                            if (!TextUtils.isEmpty(url_3w)) {
                                Intent intent = new Intent(frg.getActivity(), ActivityTest.class);
                                intent.putExtra("url_3w",url_3w);
                                frg.startActivity(intent);
                            }
                        }
                    });

                    frg.mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
                        @Override
                        public void onItemLongClick(View view, int position) {
                            Toast.makeText(frg.getActivity(),"onItemLongClick---" + position,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case MSG_GET_NEWS_MORE:
                    frg.mAdapter.notifyDataSetChanged();
                    break;
                case MSG_GET_NEWS_EMPTY:
                    if (frg.mLayoutFail!=null)
                        frg.mLayoutFail.setVisibility(View.VISIBLE);
                    if (frg.mLayoutLoading != null)
                        frg.mLayoutLoading.setVisibility(View.GONE);
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
        mPresenter.setNewsList(mType,pageIndex,true,false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        addAllListener();
    }

    private void addAllListener() {
        mLayoutFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setNewsList(mType,pageIndex,true,false);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setNewsList(mType,pageIndex,true,false);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mAdapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.isShowFooter()) {
                        pageIndex +=Urls.PAZE_SIZE;
                        mPresenter.setNewsList(mType,pageIndex ,false,true);
                        Log.d("addAllListener","onScrollStateChanged");
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                Log.d("addAllListener","lastVisibleItem---" + lastVisibleItem + "   getItemCount---" + mAdapter.getItemCount());
            }
        });
    }

    @Override
    public void setNews(ArrayList<News> data, boolean refresh, boolean loadMore) {
        if (refresh) {
            mAdapter = new NewsListAdapter(getActivity(),data);
            if (data.size() > 0) {
                mAdapter.isShowFooter(true);
            }else {
                mAdapter.isShowFooter(false);
            }

            if (data.size() == 0) {
                mHandler.sendEmptyMessage(MSG_GET_NEWS_EMPTY);
            }else {
                mHandler.sendEmptyMessage(MSG_GET_NEWS_INFO);
            }
        }

        if (loadMore) {
            if (data.size() > 0) {
                mAdapter.isShowFooter(true);
            }else {
                mAdapter.isShowFooter(false);
            }
            mAdapter.loadMore(data);
            mHandler.sendEmptyMessage(MSG_GET_NEWS_MORE);
        }

    }

    @Override
    public void setFail() {
        mHandler.sendEmptyMessage(MSG_GET_NEWS_EMPTY);
    }
}
