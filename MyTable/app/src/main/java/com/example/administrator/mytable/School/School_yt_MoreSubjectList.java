package com.example.administrator.mytable.School;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.DBUtil.DBUtil_Subject;
import com.example.administrator.mytable.School.Entiy.Subject;
import com.example.administrator.mytable.School.adapter.Subject_BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/1.
 */
public class School_yt_MoreSubjectList extends AppCompatActivity {
    private LinearLayout iBack;
    private ListView lvList;
    private Subject_BaseAdapter adapter;
    private List<Subject> subjectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_school_yt_more_subject_list);
        lvList=(ListView)super.findViewById(R.id.lvList);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       initView();
    }
    private void initView(){
        subjectList=new ArrayList<Subject>();
        subjectList.addAll(DBUtil_Subject.getSubjectData());
        adapter=new Subject_BaseAdapter(this,subjectList);
        lvList.setAdapter(adapter);
    }
}
