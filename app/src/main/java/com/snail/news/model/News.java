package com.snail.news.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.snail.common.Urls;
import com.snail.news.ActivityNews;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chengcai on 2016/10/10.
 */

public class News implements Serializable{
    private String title;
    private String imgsrc;
    private String ptime;

    public static ArrayList<News> getNewsList(int type,String json) {
        String s = null;
        switch (type) {
            case ActivityNews.NEWS_EDUCATION:
                s = Urls.EDUCATION_ID;
                break;
            case ActivityNews.NEWS_SCIENCE:
                s = Urls.TECH_ID;
                break;
            case ActivityNews.NEWS_SPORTS:
                s = Urls.SPORTS_ID;
                break;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            ArrayList<News> arrayList = gson.fromJson(jsonObject.getString(s),new TypeToken<ArrayList<News>>(){}.getType());
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", ptime='" + ptime + '\'' +
                '}';
    }
}
