package com.snail.ui.fragment;

import android.content.Intent;
import android.net.Uri;
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

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.snail.ui.activity.ActivityFamous;
import com.snail.ui.activity.ActivityTest;
import com.snail.ui.activity.Book2Activity;
import com.snail.ui.activity.QuestionActivity;
import com.snail.R;
import com.snail.mvp.dictionary.ActivityChDictionary;
import com.snail.mvp.news.ActivityNews;
import com.snail.mvp.wrong.ActivityWrongEdit;
import com.snail.transforms.CubeOutTransformer;
import com.snail.transforms.TransformerItem;
import com.snail.ui.activity.BookActivity;
import com.snail.ui.activity.NoteActivity;
import com.snail.ui.activity.PhraseActivity;
import com.snail.ui.activity.QuestionEditActivity;
import com.snail.ui.activity.WordsActivity;
import com.snail.widget.NetworkImageHolderView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/9/29.
 */

public class FragmentHome extends Fragment {
    @BindView(R.id.banner)
    ConvenientBanner mBanner;

    private ArrayList<String> netImages = new ArrayList<>();
    private ArrayList<Integer> localImages = new ArrayList<>();
    private  GridView gridview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,view);

        initBanner();
        initGridView(view);
        return view;
    }

    private void initBanner() {
        for (int i=0; i<7; i++) {
            localImages.add(getResId("ic_test_" + i, R.drawable.class));
        }
        netImages.add("http://www.ahscyz.cn/xxhsd/UploadFiles_5809/201605/2016051420552765.jpg");
        netImages.add("http://www.ahscyz.cn/xxhsd/UploadFiles_5809/201605/2016051415232666.jpg");
        netImages.add("http://www.ahscyz.cn/xxhsd/UploadFiles_5809/201605/2016051415043486.jpg");
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>(){
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },netImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
        .setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = null;
                if (position == 0) {
                    url = "http://www.ahscyz.cn/xxhsd/ShowArticle.asp?ArticleID=2366";
                }else if (position ==1) {
                    url = "http://www.ahscyz.cn/xxhsd/ShowArticle.asp?ArticleID=2360";
                }else if (position == 2) {
                    url = "http://www.ahscyz.cn/xxhsd/ShowArticle.asp?ArticleID=2358";
                }
                Uri uri = Uri.parse(url);
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
        mBanner.startTurning(3000);//设置轮播开始自动循环
        mBanner.setScrollDuration(2000);//设置滑动速度
        try {
            mBanner.getViewPager().setPageTransformer(true,new TransformerItem(CubeOutTransformer.class).clazz.newInstance());//设置轮播动画
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    private void initGridView(View view) {
        gridview = (GridView) view.findViewById(R.id.GridView);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
//        for(int i = 1;i < 10;i++)
//        {
//            HashMap<String, Object> map = newnote HashMap<String, Object>();
//            map.put("ItemImage", R.drawable.rulers);
//            map.put("ItemText", ""+i);
//            meumList.add(map);
//        }
        initMenuList(meumList);
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
                        Intent intent = new Intent();
                        switch (arg2) {
                            case 0:
                                Intent intent1 = new Intent(getActivity(),ActivityWrongEdit.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(),ActivityChDictionary.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent wordIntent = new Intent(getActivity(), WordsActivity.class);
                                startActivity(wordIntent);
                                break;
                            case 3:
                                intent.setClass(getContext(), NoteActivity.class);
                                startActivity(intent);
                                break;
                            case 5:
                                intent.setClass(getContext(), QuestionActivity.class);
                                startActivity(intent);
                                break;
                            case 6:
                                // 暂时放这
                                intent.setClass(getContext(), ActivityFamous.class);
                                startActivity(intent);
                                break;

                            case 4:
                                Intent intentPhrase = new Intent(getActivity(),PhraseActivity.class);
                                startActivity(intentPhrase);
                                break;
                            case 7:
                                intent.setClass(getContext(), Book2Activity.class);
                                startActivity(intent);
                                break;
                            case 8:
                                intent.setClass(getContext(), ActivityNews.class);
                                startActivity(intent);
                                break;
                            default:
                                int index=arg2+1;//id是从0开始的，所以需要+1
                                Toast.makeText(getActivity(), "你按下了选项："+index, Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }
        );
    }

    private void initMenuList(ArrayList<HashMap<String, Object>> meumList) {
        // map1 错误回顾
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("ItemImage", R.drawable.palace_1);
        map1.put("ItemText", "错误编辑");
        meumList.add(map1);

        // map2 今日闯关
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("ItemImage", R.drawable.palace_2);
        map2.put("ItemText", "新华字典");
        meumList.add(map2);


        // map3 高频单词
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("ItemImage", R.drawable.palace_3);
        map3.put("ItemText", "高频单词");
        meumList.add(map3);


        // map4 备忘录
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("ItemImage", R.drawable.palace_4);
        map4.put("ItemText", "备忘录");
        meumList.add(map4);

        // map5 题库
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("ItemImage", R.drawable.palace_5);
        map5.put("ItemText", "成语查询");
        meumList.add(map5);

        // map6 题库
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("ItemImage", R.drawable.palace_6);
        map6.put("ItemText", "互助问答");
        meumList.add(map6);


        // map7 题库
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("ItemImage", R.drawable.palace_7);
        map7.put("ItemText", "名人名言");
        meumList.add(map7);

        // map8 题库
        HashMap<String, Object> map8 = new HashMap<String, Object>();
        map8.put("ItemImage", R.drawable.palace_8);
        map8.put("ItemText", "推荐书籍");
        meumList.add(map8);

        // map9 题库
        HashMap<String, Object> map9 = new HashMap<String, Object>();
        map9.put("ItemImage", R.drawable.palace_9);
        map9.put("ItemText", "News");
        meumList.add(map9);
    }

}
