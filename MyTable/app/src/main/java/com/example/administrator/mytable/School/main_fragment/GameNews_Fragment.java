package com.example.administrator.mytable.School.main_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.RequestPackage;
import com.example.administrator.mytable.School.WebView_News_Detil_Activity2;
import com.example.administrator.mytable.School.adapter.News_BaseAdapter22;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class GameNews_Fragment extends Fragment
        implements PullToRefreshBase.OnRefreshListener,RequestPackage.callBack {
    private PullToRefreshListView pullVip;
    private News_BaseAdapter22 adapter;
    private List<Theme> newsList;
    private int pageNo;
    private int pageSize;
    private String mParam1;
    private static final String ARG_PARAM1="channelName";
    private RequestPackage pac;
    public static GameNews_Fragment newsInstance(String channelName){
        GameNews_Fragment fragment=new GameNews_Fragment();
        Bundle args=new Bundle();
        args.putString(ARG_PARAM1,channelName);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mParam1=getArguments().getString(ARG_PARAM1);
        }
        pac=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());//全局的请求
        pac.setCallBack(this);
//        pac.getRequest(mParam1);///////////////////////////////////////////////
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_yt__to_game_pull,null);
        pullVip=(PullToRefreshListView)view.findViewById(R.id.pullVip);
        initPullRefreshView();
        return view;
    }
    private void initPullRefreshView(){
        newsList=new ArrayList<Theme>();
        adapter=new News_BaseAdapter22(getContext(),newsList);
        pullVip.setAdapter(adapter);
        pullVip.setOnRefreshListener(this);
        pullVip.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pullVip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Theme theme=newsList.get(position-1);
                Intent intent=new Intent(getActivity(),WebView_News_Detil_Activity2.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",theme);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        pullVip.setRefreshing();
    }
    @Override
    public void call(List list) {
        newsList.clear();
        newsList.addAll(list);
//        pullVip.onRefreshComplete();
        handler.sendEmptyMessage(0);

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    pullVip.onRefreshComplete();
                    break;
            }
        }
    };
    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        pac.getRequest(mParam1);
    }
}
