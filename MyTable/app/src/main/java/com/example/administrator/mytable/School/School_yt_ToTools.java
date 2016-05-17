package com.example.administrator.mytable.School;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.yt_WebView.ToGoJobActivity;
import com.example.administrator.mytable.School.yt_WebView.ToHelpStudentActivity;
import com.example.administrator.mytable.School.yt_WebView.ToMathActivity;
import com.example.administrator.mytable.School.yt_WebView.ToStudentWorkActivity;
import com.example.administrator.mytable.School.yt_WebView.ToYouthLeagueActivity;
import com.example.administrator.mytable.School.yt_WebView.ToofficeActivity;
import com.example.administrator.mytable.School.yt_WebView.YantaiMainActivity;

public class School_yt_ToTools extends AppCompatActivity {
    private LinearLayout iBack;
    private TextView YantaiMain;
    private TextView Math;
    private TextView StudentWork;
    private TextView office;
    private TextView YouthLeague;
    private TextView GoJob;
    private TextView HelpStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__to_tools);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        YantaiMain=(TextView)super.findViewById(R.id.YantaiMain);
        Math=(TextView)super.findViewById(R.id.Math);
        StudentWork=(TextView)super.findViewById(R.id.StudentWork);
        office=(TextView)super.findViewById(R.id.office);
        GoJob=(TextView)super.findViewById(R.id.GoJob);
        HelpStudent=(TextView)super.findViewById(R.id.HelpStudent);
        initView();
    }
    public void toYantaiMain(View view){
        Intent intent=new Intent(this, YantaiMainActivity.class);
        startActivity(intent);
    }
    public void toMath(View view){
        Intent intent=new Intent(School_yt_ToTools.this, ToMathActivity.class);
        startActivity(intent);
    }
    public void toStudentWork(View view){
        Intent intent=new Intent(School_yt_ToTools.this, ToStudentWorkActivity.class);
        startActivity(intent);
    }
    public void tooffice(View view){
        Intent intent=new Intent(School_yt_ToTools.this, ToofficeActivity.class);
        startActivity(intent);
    }
    public void toYouthLeague(View view){
        Intent intent=new Intent(School_yt_ToTools.this, ToYouthLeagueActivity.class);
        startActivity(intent);
    }
    public void toGoJob(View view){
        Intent intent=new Intent(School_yt_ToTools.this, ToGoJobActivity.class);
        startActivity(intent);
    }
    public void toHelpStudent(View view){
        Intent intent=new Intent(School_yt_ToTools.this, ToHelpStudentActivity.class);
        startActivity(intent);
    }
    private void initView() {
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
