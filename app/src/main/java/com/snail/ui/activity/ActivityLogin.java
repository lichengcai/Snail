package com.snail.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.snail.R;

/**
 * Created by lichengcai on 2016/9/30.
 */

public class ActivityLogin extends ActivityBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLogin.this.startActivity(new Intent(ActivityLogin.this,MainActivity.class));
                ActivityLogin.this.finish();
            }
        });

    }
}
