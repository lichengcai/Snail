package com.snail.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.snail.R;
import com.snail.mvp.news.listener.OnWindowFocusListener;
import com.snail.mvp.wrong.ActivityWrongEdit;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentWrite extends Fragment {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_fail)
    RelativeLayout mLayout_fail;
    @BindView(R.id.layout_loading)
    RelativeLayout mLayout_loading;
    @BindView(R.id.mFloatingActionButton)
    FloatingActionButton mFloatingActionButton;

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
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityWrongEdit.class));
            }
        });

    }




}
