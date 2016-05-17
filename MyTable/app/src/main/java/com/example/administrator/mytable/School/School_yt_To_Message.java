package com.example.administrator.mytable.School;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mytable.R;
public class School_yt_To_Message extends AppCompatActivity {
    private LinearLayout iBack;
    private ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt_to__message_1);
        add=(ImageView)super.findViewById(R.id.add);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        initView();
    }
    private void initView(){
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_yt_To_Message.this,School_yt_MessageAdd.class);
                startActivity(intent);
            }
        });
    }
}
