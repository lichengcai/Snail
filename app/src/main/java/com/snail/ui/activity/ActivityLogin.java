package com.snail.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.feedback.Resources;
import com.avos.avoscloud.okhttp.internal.Internal;
import com.snail.R;
import com.snail.biz.UserBiz;

import java.util.Date;

/**
 * Created by lichengcai on 2016/9/30.
 */

public class ActivityLogin extends ActivityBase {
    private Button loginBtn;
    private Button registerBtn;
    private EditText inputUserName;
    private EditText inputUserPwd;
    private String shareUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public long getUserStatus() {
        //1、获取Preferences
        SharedPreferences settings = getSharedPreferences("userStatus", 0);
        //2、取出数据
        String loginTime = settings.getString("loginTime","0");
        shareUserName =  settings.getString("userName","");
        long time = Long.valueOf(loginTime).longValue();
        return time;
    }
    public boolean isOverdue() {
        Date date = new Date();
        long lastTime = getUserStatus();
        Log.d("time",lastTime+ "->" +date.getTime());
        if (date.getTime() - lastTime >= 3600) {
//        if (date.getTime() - lastTime >= 3600*1000) {
            return true;
        }
        return false;
    }

    public void saveUserStatus(String userName) {
        //1、打开Preferences，名称为setting，如果存在则打开它，否则创建新的Preferences
        SharedPreferences settings = getSharedPreferences("userStatus", 0);
        //2、让setting处于编辑状态
        SharedPreferences.Editor editor = settings.edit();
        //3、存放数据
        Date date = new Date();
        editor.putString("loginTime", date.getTime() + "");
        editor.putString("userName", userName);
        //4、完成提交
        editor.commit();
    }
    private void bindEvent() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = getUserName();
                String pwd = getPwd();
                if ("".equals(userName) || "".equals(pwd)) {
                    Toast.makeText(ActivityLogin.this, "userName or password can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                new UserBiz(ActivityLogin.this).queryUserByNamePwd(userName, pwd);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * register login
                 */
                String userName = getUserName();
                String pwd = getPwd();
                new UserBiz(ActivityLogin.this).saveUser(userName, pwd);
            }
        });
    }

    private void init() {
        if (!isOverdue()) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("userName", shareUserName);
            this.startActivity(intent);
            this.finish();
        }
        inputUserName = (EditText) findViewById(R.id.user_name);
        inputUserPwd = (EditText) findViewById(R.id.user_pwd);

        inputUserName.setText("panyan".toCharArray(), 0, "panyan".length());
        inputUserPwd.setText("12345".toCharArray(), 0, "12345".length());

        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.register_btn);


        bindEvent();
    }

    public void jumpToMain() {
        Log.d("jumpToMain","jumpToMain invode by userbiz");
        Intent intent = new Intent(this,MainActivity.class);
        String userName = getUserName();
        intent.putExtra("userName", userName);
        this.startActivity(intent);
        this.finish();

    }

    public String getUserName() {
        String userName = inputUserName.getText().toString();
        Log.d("username",userName);
        return userName;
    }
    public String getPwd() {
        String pwd = inputUserPwd.getText().toString();
        return pwd;
    }
 }
