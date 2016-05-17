package com.example.administrator.mytable.School;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mytable.R;

public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent  intent = new Intent(Welcome.this, MainActivity_Net.class); //已安装启动
                Welcome.this.startActivity(intent);
                Welcome.this.finish();
            }
    }, 500);
    }
}
