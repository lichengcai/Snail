package com.snail.biz;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;
import com.snail.ui.activity.ActivityLogin;
import com.snail.ui.activity.MainActivity;

import java.util.List;

/**
 * Created by zhangshibiao on 16/10/8.
 */

public class UserBiz {
    private Context context;
    public UserBiz(Context context) {
        this.context = context;

    }
    /**
     * query user
     * @param userName
     * @param password
     * @return
     */
    public boolean queryUserByNamePwd(final String userName, String password) {
        Log.d("queryUserByNamePwd",userName + "->" + password);
        AVUser.logInInBackground(userName, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                Toast.makeText(context, "login success",Toast.LENGTH_SHORT).show();
                ActivityLogin al = (ActivityLogin)context;
                al.saveUserStatus(userName);
                al.jumpToMain();
            }
        });
        return true;
        /**
         * 这里走了弯路,不能通过比对密码的方式来判断登陆,leancloud 已经提供来登陆的api
         * 详情看 https://leancloud.cn/docs/leanstorage_guide-android.html#用户
         */
//        AVQuery<AVObject> query = new AVQuery<>("_User");
//        query.whereEqualTo("username", userName);
//        query.whereEqualTo("password", password);
//        Log.d("queryUserByNamePwd",userName + "->" + password);
//        query.findInBackground(new FindCallback<AVObject>() {
//            @Override
//            public void done(List<AVObject> list, AVException e) {
//                List<AVObject> priorityEqualsZeroTodos = list;
//                // 符合 userName,password 的 Todo 数组
//
//                if (e != null) {
//                    Log.d("queryUserByNamePwd",e.toString());
//                    Toast.makeText(context, "no user",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                int size = priorityEqualsZeroTodos.size();
//                Log.d("query list", priorityEqualsZeroTodos.toString());
//                if (size < 1) {
//                    Log.d("queryUserByNamePwd","no user" + userName);
//                    Toast.makeText(context, "no user",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                ActivityLogin al = (ActivityLogin)context;
//                al.jumpToMain();
//
//            }
//        });
    }

    public boolean saveUser(String userName, String password) {
        AVObject user = new AVObject("_User");
        user.put("username", userName);
        user.put("password", password);
        Log.d("saveUser","saveUser" + password);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                Log.d("saveUser","registerBtn.saveInBackground");
                if(e == null){
                    Log.d("saveUser","success!");
                    Toast.makeText(context,"register success",Toast.LENGTH_SHORT).show();
                    ActivityLogin al = (ActivityLogin)context;
                    al.jumpToMain();
                }
                else {
                    Log.d("saveUser",e.toString());
                    int code = e.getCode();
                    if (code == 202) {
                        // 更多错误码处理 https://leancloud.cn/docs/error_code.html#_202
                        Toast.makeText(context,"Username has already been taken.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return true;
    }

}
