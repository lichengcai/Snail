package com.snail.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.snail.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentMore extends Fragment {
    @BindView(R.id.text_summary)
    TextView mText_summary;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText_summary.setText("    Snail的创意来源于蜗牛，“我要一步一步往上爬，等待阳光静静看着它的脸，小小的天有大大的梦想。任风吹干留过的泪和汗，总有一天我有属于我的天。”周杰伦的<蜗牛>，唱出了我内心的想法，我们要像蜗牛一样，顽强和坚持不懈，不断努力，为自己的理想而奋斗的意思！");
    }
}
