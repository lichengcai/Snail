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
    private String zi;
    private String pinyin;
    private String bihua;
    private String jinjie;
    private String xiangjie;


    @Override
    public String toString() {
        return "HanZi{" +
                "zi='" + zi + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", bihua='" + bihua + '\'' +
                ", jijie='" + jinjie + '\'' +
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
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
        return jinjie;
    }

    public void setJijie(String jijie) {
        this.jinjie = jijie;
    }

    public String getXiangjie() {
        return xiangjie;
    }

    public void setXiangjie(String xiangjie) {
        this.xiangjie = xiangjie;
    }
}
