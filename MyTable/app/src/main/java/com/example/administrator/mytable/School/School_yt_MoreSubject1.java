package com.example.administrator.mytable.School;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Subject_Head_Net;
import com.example.administrator.mytable.School.HttpURL.HttpURL;
import com.example.administrator.mytable.School.MyApplication.MyApplication;

public class School_yt_MoreSubject1 extends AppCompatActivity {
    private LinearLayout iBack;
    private ImageView image1;
    private TextView title;
    private Subject_Head_Net HeadNet;
    private MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__more_subject1);
        image1= (ImageView) super.findViewById(R.id.image1);
        title= (TextView) super.findViewById(R.id.title);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        app=(MyApplication)super.getApplication();
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        HeadNet= (Subject_Head_Net) bundle.get("data");
        title.setText(HeadNet.getSubject_title());
        app.getImageLoader().get(HttpURL.HOST+"SchoolLife/" + HeadNet.getSubject_url(),
                ImageLoader.getImageListener(image1,
                        R.mipmap.cc_default_news_img, R.mipmap.cc_default_news_img));
    }
}
