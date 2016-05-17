package com.example.administrator.mytable.School.fragment_no_need;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.DBUtil.DBUtil_News;
import com.example.administrator.mytable.School.Entiy.News;
import com.example.administrator.mytable.School.adapter.News_BaseAdapter;
import com.example.administrator.mytable.School.adapter.News_BaseAdapter_Net;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

//考研分析
public class Go_Aborad_Anaylyze_Fragment extends Fragment implements PullToRefreshBase.OnRefreshListener2{
     private PullToRefreshListView vip;
    private List<News> newsList;
    private News_BaseAdapter adapter;
    private int pageNo;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.school_go_aborad_analyze_fragment,null);
        vip=(PullToRefreshListView)view.findViewById(R.id.vip);
        initView();
        return view;
    }
    private void initView(){
        newsList=new ArrayList<News>();
        adapter=new News_BaseAdapter(context,newsList);//???///////context自己定义的//////////////??????????
        vip.setAdapter(adapter);
        vip.setRefreshing();
        vip.setOnRefreshListener(this);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    vip.onRefreshComplete();
                    break;
            }
        }
    };
    private void load(){
        newsList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsList.addAll(DBUtil_News.getNewsData(1,20));
            }
        }).start();
    }
    private void loadMore(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsList.addAll(DBUtil_News.getNewsData(pageNo,20));
            }
        }).start();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pageNo=1;
        load();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
          if(pageNo==4){
              Toast.makeText(context,"数据加载完毕",Toast.LENGTH_LONG).show();////////context自己定义的/////////////////
              vip.onRefreshComplete();
          }else{
              pageNo++;
              loadMore();
          }
    }
}
