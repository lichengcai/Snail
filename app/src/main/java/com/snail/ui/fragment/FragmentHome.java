package com.snail.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.snail.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentHome extends Fragment {
    private  GridView gridview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initGridView(view);
        return view;
    }
    private void initGridView(View view) {
        gridview = (GridView) view.findViewById(R.id.GridView);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
        for(int i = 1;i < 10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.rulers);
            map.put("ItemText", ""+i);
            meumList.add(map);
        }
        SimpleAdapter saItem = new SimpleAdapter(getActivity(),
                meumList, //数据源
                R.layout.item, //xml实现
                new String[]{"ItemImage","ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage,R.id.ItemText});  //对应R的Id

        //添加Item到网格中
        gridview.setAdapter(saItem);
        //添加点击事件
        gridview.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
                    {
                        int index=arg2+1;//id是从0开始的，所以需要+1
                        Toast.makeText(getActivity(), "你按下了选项："+index, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "你按下了选项："+index, 0).show();
                        //Toast用于向用户显示一些帮助/提示
                    }
                }
        );
    }
}
