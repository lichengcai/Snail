package com.snail.bean;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.snail.mvp.news.model.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/17.
 */

public class HanZi {
    private String hanzi;
    private String pinyin;
    private String bihua;
    private String jianjie;
    private String xiangjie;


    @Override
    public String toString() {
        return "HanZi{" +
                "zi='" + hanzi + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", bihua='" + bihua + '\'' +
                ", jijie='" + jianjie + '\'' +
                ", xiangjie='" + xiangjie + '\'' +
                '}';
    }

    public static HanZi getHanzi(String json) {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(json);
            ArrayList<HanZi> arrayList = gson.fromJson(jsonObject.getString("result"),new TypeToken<ArrayList<HanZi>>(){}.getType());
            for (int i=0; i<arrayList.size(); i++) {
                Log.d("getHanzi","---" + arrayList.get(i).toString());
            }
            return arrayList.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getZi() {
        return hanzi;
    }

    public void setZi(String zi) {
        this.hanzi = zi;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getBihua() {
        return bihua;
    }

    public void setBihua(String bihua) {
        this.bihua = bihua;
    }

    public String getJijie() {
        return jianjie;
    }

    public void setJijie(String jijie) {
        this.jianjie = jijie;
    }

    public String getXiangjie() {
        return xiangjie;
    }

    public void setXiangjie(String xiangjie) {
        this.xiangjie = xiangjie;
    }
}
