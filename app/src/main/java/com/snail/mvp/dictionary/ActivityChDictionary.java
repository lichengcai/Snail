package com.snail.mvp.dictionary;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.R;
import com.snail.bean.HanZi;
import com.snail.ui.activity.ActivityBase;
import com.snail.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/10/17.
 */

public class ActivityChDictionary extends ActivityBase {
    @BindView(R.id.etSearch)
    EditText mEdit;
    @BindView(R.id.text_zi)
    TextView mTextZi;
    @BindView(R.id.text_pinyin)
    TextView mTextPinyin;
    @BindView(R.id.text_bihua)
    TextView mTextBihua;
    @BindView(R.id.text_jinjie)
    TextView mTextJinjie;
    @BindView(R.id.text_xiangjie)
    TextView mTextXiangjie;
    @BindView(R.id.btn_search)
    Button mBtn_seach;

    private String mKey;
    private String url = "http://api.avatardata.cn/XinHuaZiDian/LookUp?key=222d4f0506d04a0fa56e12c196fdd7df&content=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_dictionary);
        ButterKnife.bind(this);

        mBtn_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChinese();
            }
        });
    }

    private void getChinese() {

        if (TextUtils.isEmpty(mEdit.getText().toString())) {
            Toast.makeText(ActivityChDictionary.this,"请输入要查询的汉字",Toast.LENGTH_SHORT).show();
        }else {
            mKey = mEdit.getText().toString();
        }

        String finalUlr = url + mKey;
        HttpUtils.get(finalUlr, new HttpUtils.LoadCallback() {
            @Override
            public void success(String json) {
                Log.d("tag","success---" + json);

            }

            @Override
            public void fail(IOException e) {

            }
        });


    }


}
