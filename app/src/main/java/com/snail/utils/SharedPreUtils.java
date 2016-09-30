package com.snail.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreUtils {
    private static final String SHAREDPREUTILS = "SharedPreUtils";
    private static final String USERINFO = "user_info";
    private static final String LOGINUSER= "login_user";
    private static SharedPreferences sp;

    public static void init(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREDPREUTILS,0);
        }
    }

    public static String getSid() {
        if (sp != null) {
            if (TextUtils.isEmpty(sp.getString(LOGINUSER,""))){
                return "";
            }else {
//                LoginUser loginUser = LoginUser.getLoginUser(sp.getString(LOGINUSER,""));
//                return loginUser.getSid();
            }
        }
        return "";
    }

    public static void setUserInfo(String userInfo) {
        if (sp != null) {
            sp.edit().putString(USERINFO,userInfo).apply();
        }
    }

    public static void setLoginUser(String loginUser) {
        if (sp != null) {
            sp.edit().putString(LOGINUSER,loginUser).apply();
        }
    }
}
