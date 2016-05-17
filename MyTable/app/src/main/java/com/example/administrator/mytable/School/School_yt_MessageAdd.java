package com.example.administrator.mytable.School;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.yt_LinkMan.yt_LinkMan;

public class School_yt_MessageAdd extends AppCompatActivity {
    private LinearLayout iBack;
    private ImageView image;
    private ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__message_add);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        image=(ImageView)super.findViewById(R.id.image);
        add=(ImageView)super.findViewById(R.id.add);
        initViewListener();

    }
    private void initViewListener() {
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_yt_MessageAdd.this,yt_LinkMan.class);
                startActivity(intent);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(School_yt_MessageAdd.this,"请先输入姓名",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
