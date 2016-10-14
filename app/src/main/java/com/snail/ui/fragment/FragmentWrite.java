package com.snail.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentWrite extends Fragment implements OnWindowFocusListener{
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_fail)
    RelativeLayout mLayout_fail;
    @BindView(R.id.layout_loading)
    RelativeLayout mLayout_loading;
    @BindView(R.id.boom)
    BoomMenuButton mBoom;
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

    }


    @Override
    public void onWidowFocus() {
        initBoomButton();

    }

    /**
     * 初始化BoomMenuButton
     */
    private void initBoomButton() {
        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(getActivity(), R.color.colorPrimary);
        }

        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(getActivity(), R.drawable.mark), subButtonColors[1], "add")
                .addSubButton(ContextCompat.getDrawable(getActivity(), R.drawable.refresh), subButtonColors[0], "share")
                .addSubButton(ContextCompat.getDrawable(getActivity(), R.drawable.copy), subButtonColors[0], "share")
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.CIRCLE_3_1)
                .subButtonTextColor(ContextCompat.getColor(getActivity(), R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(mBoom);
    }

}
