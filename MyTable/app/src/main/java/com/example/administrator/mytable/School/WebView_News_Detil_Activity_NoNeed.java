package com.example.administrator.mytable.School;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;

public class WebView_News_Detil_Activity_NoNeed extends AppCompatActivity {
    private TextView title;
    private TextView date;
    private TextView detail;
    private TextView toSliding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__detil_);
        toSliding=(TextView)super.findViewById(R.id.iBack);
        toSliding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView_News_Detil_Activity_NoNeed.this.finish();
            }
        });
        title=(TextView)super.findViewById(R.id.title);
        date=(TextView)super.findViewById(R.id.date);
        detail=(TextView)super.findViewById(R.id.details);
        initView();
    }
    private void initView(){
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        Theme theme= (Theme) bundle.get("data");
        title.setText(theme.getTitle());
        date.setText(theme.getTime());
        detail.setText(Html.fromHtml(theme.getContent()));
    }
}
