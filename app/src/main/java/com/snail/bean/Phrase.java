package com.snail.bean;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chengcai on 2016/10/30.
 */

public class Phrase {
    private String id;
    private String name;

    public static ArrayList<Phrase> getPhrase(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            ArrayList<Phrase> arrayList = gson.fromJson(jsonObject.getString("result"),new TypeToken<ArrayList<Phrase>>(){}.getType());
            for (int i=0; i<arrayList.size(); i++) {

            Log.d("getPhrase","array---" + arrayList.get(i).toString());
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String toString() {
        return "Phrase{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
