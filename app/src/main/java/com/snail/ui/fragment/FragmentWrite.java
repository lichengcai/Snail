package com.snail.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.snail.R;
import com.snail.mvp.news.listener.OnWindowFocusListener;
import com.snail.mvp.wrong.ActivityWrongEdit;
import com.snail.mvp.wrong.adapter.WrongAdapter;
import com.snail.mvp.wrong.model.WrongBean;
import com.snail.mvp.wrong.persenter.WrongPresenter;
import com.snail.mvp.wrong.persenter.WrongPresenterImpl;
import com.snail.mvp.wrong.view.WrongView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentWrite extends Fragment implements WrongView{
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_fail)
    RelativeLayout mLayout_fail;
    @BindView(R.id.layout_loading)
    RelativeLayout mLayout_loading;

    private TextView mText_hint;
    private WriteHandler mHandler = new WriteHandler(this);

    private static final int MSG_GET_WRONG_SUCCESS = 0;
    private static final int MSG_GET_WRONG_FAIL = 1;
    private static final int MSG_GET_WRONG_EMPTY = 2;

    private WrongPresenter mPresenter;
    private WrongAdapter mAdapter;

    private static class WriteHandler extends Handler {
        private WeakReference<FragmentWrite> ref;
        private FragmentWrite write;

        public WriteHandler(FragmentWrite fragmentWrite) {
            ref  = new WeakReference<>(fragmentWrite);
            write = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_WRONG_SUCCESS:
                    if (write.mSwipeLayout!=null && write.mSwipeLayout.isRefreshing()) {
                        write.mSwipeLayout.setRefreshing(false);
                    }
                    if (write.mLayout_fail != null)
                        write.mLayout_fail.setVisibility(View.GONE);
                    if (write.mLayout_loading != null)
                        write.mLayout_loading.setVisibility(View.GONE);
                    write.mRecyclerView.setAdapter(write.mAdapter);
                    break;
                case MSG_GET_WRONG_FAIL:
                    if (write.mSwipeLayout!=null && write.mSwipeLayout.isRefreshing()) {
                        write.mSwipeLayout.setRefreshing(false);
                    }
                    if (write.mLayout_loading !=null)
                        write.mLayout_loading.setVisibility(View.GONE);
                    if (write.mLayout_fail !=null)
                        write.mLayout_fail.setVisibility(View.VISIBLE);
                    break;
                case MSG_GET_WRONG_EMPTY:
                    if (write.mSwipeLayout!=null && write.mSwipeLayout.isRefreshing()) {
                        write.mSwipeLayout.setRefreshing(false);
                    }
                    if (write.mLayout_loading !=null)
                        write.mLayout_loading.setVisibility(View.GONE);
                    if (write.mLayout_fail !=null)
                        write.mLayout_fail.setVisibility(View.VISIBLE);
                    write.mText_hint.setText(write.getResources().getString(R.string.data_empty));
                    break;
            }
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        addAllListener();
    }

    private void init() {
        mText_hint = (TextView) mLayout_fail.findViewById(R.id.text_hint);
        mPresenter = new WrongPresenterImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.queryWrongBean();
    }

    private void addAllListener() {
        mLayout_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.queryWrongBean();
            }
        });
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.queryWrongBean();
            }
        });
    }

    @Override
    public void setWrongImage(Bitmap bitmap) {

    }

    @Override
    public void showUploadSuccess() {

    }

    @Override
    public void showUploadFail() {

    }

    @Override
    public void setWrongBeanSuccess(ArrayList<WrongBean> arrayList) {

        mAdapter = new WrongAdapter(getActivity(),arrayList);
        mHandler.sendEmptyMessage(MSG_GET_WRONG_SUCCESS);
    }

    @Override
    public void setWrongBeanFail() {
        mHandler.sendEmptyMessage(MSG_GET_WRONG_FAIL);
    }

    @Override
    public void setWrongBeanEmpty() {
        mHandler.sendEmptyMessage(MSG_GET_WRONG_EMPTY);
    }
}
