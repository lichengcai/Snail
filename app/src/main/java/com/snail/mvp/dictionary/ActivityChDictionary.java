package com.snail.mvp.dictionary;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.snail.R;
import com.snail.ui.activity.ActivityBase;
import com.snail.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lichengcai on 2016/10/17.
 */

public class ActivityChDictionary extends ActivityBase {
    @BindView(R.id.text_zi)
    TextView mTextZi;
    @BindView(R.id.text_pinyin)
    TextView mTextPinyin;
    @BindView(R.id.text_bihua)
    TextView mTextBihua;
    @BindView(R.id.text_jijie)
    TextView mTextJijie;
    @BindView(R.id.text_xiangjie)
    TextView mTextXiangjie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_dictionary);
        getChinese();
    }

    private void getChinese() {
    }


}
