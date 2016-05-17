package com.example.administrator.mytable.School.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mytable.School.DBUtil.DBUtil_News;
import com.example.administrator.mytable.School.Entiy.News;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ChannelPagerAdapter extends PagerAdapter{
    private Context context;
    private List<String> channelNames;

    public ChannelPagerAdapter() {
    }
    public ChannelPagerAdapter(Context context, List<String> channelNames) {
        this.context = context;
        this.channelNames = channelNames;
    }
    @Override
    public int getCount() {
        return channelNames.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String channelName=channelNames.get(position);//这里会网络传参
        PullToRefreshListViewConfig  sv=new PullToRefreshListViewConfig(channelName);
        container.addView(sv.getView());//////////////////////////////////////////
        return sv.getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((PullToRefreshListView)object);
    }
//==================================================================================================
    private class PullToRefreshListViewConfig{  //Config配置，布局；显示配置信息命令
         private PullToRefreshListView lvSchoolNews;
         private News_BaseAdapter adapter;
         private List<News> schoolNewsList;
         private int pageNo=1;
         private int pageSize=10;
         private String channelName;

    public PullToRefreshListViewConfig(String channelName){ //构造函数
               this.channelName=channelName;
               init();
    }
    private void init(){ //刷新模式
        // 这里Context代表ChannelPagerAdapter.PullToRefreshListViewConfig
        schoolNewsList=new ArrayList<News>();
        lvSchoolNews=new PullToRefreshListView(context);
//        lvSchoolNews.setLayoutParams(new AbsListView.LayoutParams(//动态构建的布局
//                AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.WRAP_CONTENT));
        lvSchoolNews.setMode(PullToRefreshListView.Mode.BOTH);
        adapter=new News_BaseAdapter(context,schoolNewsList);
        lvSchoolNews.setAdapter(adapter);
        lvSchoolNews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                load();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (pageNo == 4) {
                    Toast.makeText(context, "数据加载完成", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lvSchoolNews.onRefreshComplete();
                        }
                    }, 2000);
                } else {
                    pageNo++;
                    loadMore();
                }
            }
        });
        lvSchoolNews.setRefreshing();//自动刷新
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    lvSchoolNews.onRefreshComplete();
                    break;
            }
        }
    };
    private void load(){
        schoolNewsList.clear();//////////////////////////////////////
        new Thread(new Runnable() {
            @Override
            public void run() {
                schoolNewsList.addAll(DBUtil_News.getNewsChannelData(pageNo, pageSize, channelName));
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    private void loadMore(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                schoolNewsList.addAll(DBUtil_News.getNewsChannelData(pageNo, pageSize, channelName));
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    public PullToRefreshListView getView(){
        return lvSchoolNews;
    }
}

}

