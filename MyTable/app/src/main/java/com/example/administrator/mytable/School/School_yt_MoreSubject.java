package com.example.administrator.mytable.School;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.DBUtil.DBUtil_Subject;
import com.example.administrator.mytable.School.Entiy.Subject;
import com.example.administrator.mytable.School.adapter.Subject_BaseAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class School_yt_MoreSubject extends AppCompatActivity
        implements View.OnClickListener,PullToRefreshBase.OnRefreshListener{
    private PullToRefreshListView moreSubject;
    private LinearLayout iBack;
    private Subject_BaseAdapter adapter;
    private List<Subject> subjectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__more_subject);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
//        moreSubject=(PullToRefreshListView)super.findViewById(R.id.moreSubject);
        initView();
    }
    private void initView(){
        subjectList=new ArrayList<Subject>();
        adapter=new Subject_BaseAdapter(this,subjectList);
        moreSubject.setAdapter(adapter);
        moreSubject.setMode(PullToRefreshBase.Mode.BOTH);
        moreSubject.setOnRefreshListener(this);
        iBack.setOnClickListener(this);
        moreSubject.setRefreshing();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    moreSubject.onRefreshComplete();
                    break;
            }
        }
    };
    private void load(){
        subjectList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                subjectList.addAll(DBUtil_Subject.getSubjectData());
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }
        },1000);
    }
}
