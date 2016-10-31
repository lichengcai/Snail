package com.snail.bean;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/10/31.
 */

public class Famous {
    private String famous_saying;
    private String famous_name;

    @Override
    public String toString() {
        return "Famous{" +
                "famous_saying='" + famous_saying + '\'' +
                ", famous_name='" + famous_name + '\'' +
                '}';
    }

    public static ArrayList<Famous> getFamous(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Gson gson = new Gson();
            ArrayList<Famous> arrayList = gson.fromJson(jsonObject.getString("result"),new TypeToken<ArrayList<Famous>>(){}.getType());
            for (int i=0; i<arrayList.size(); i++) {

                Log.d("getPhrase","array---" + arrayList.get(i).toString());
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getFamous_saying() {
        return famous_saying;
    }

    public void setFamous_saying(String famous_saying) {
        this.famous_saying = famous_saying;
    }

    public String getFamous_name() {
        return famous_name;
    }

    public void setFamous_name(String famous_name) {
        this.famous_name = famous_name;
    }
}
