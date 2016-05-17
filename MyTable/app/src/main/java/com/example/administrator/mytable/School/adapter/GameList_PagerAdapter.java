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
 * Created by Administrator on 2015/12/27.
 */
public class GameList_PagerAdapter extends PagerAdapter{
    private Context context;
    private List<String> channelNames;

    public GameList_PagerAdapter() {
    }

    public GameList_PagerAdapter(Context context, List<String> channelNames) {
        this.context = context;
        this.channelNames = channelNames;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public int getCount() {
        return channelNames.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String channel=channelNames.get(position);
        PullToRefreshListViewConfig fig=new PullToRefreshListViewConfig(channel);
        container.addView(fig.getView());
        return fig.getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((PullToRefreshListView)object);
    }

    private class PullToRefreshListViewConfig{
        private PullToRefreshListView lvNews;
        private News_BaseAdapter adapter;
        private List<News> newsList;
        private int pageNo=1;
        private int pageSize=20;
        private String channelName;
        public PullToRefreshListViewConfig(String channelName){
            this.channelName=channelName;
            init();
        }
        private void init(){
            newsList=new ArrayList<News>();
            lvNews=new PullToRefreshListView(context);
            lvNews.setMode(PullToRefreshBase.Mode.BOTH);
            adapter=new News_BaseAdapter(context,newsList);
            lvNews.setAdapter(adapter);
            lvNews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
                                lvNews.onRefreshComplete();
                            }
                        }, 2000);
                    } else {
                        pageNo++;
                        loadMore();
                    }
                }
            });
            lvNews.setRefreshing();
        }
        private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        adapter.notifyDataSetChanged();
                        lvNews.onRefreshComplete();
                        break;
                }
            }
        };
        private void load(){
            newsList.clear();//////////////////////////////////////
            new Thread(new Runnable() {
                @Override
                public void run() {
                    newsList.addAll(DBUtil_News.getNewsChannelData(pageNo, pageSize, channelName));
                    handler.sendEmptyMessage(0);
                }
            }).start();
        }
        private void loadMore(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    newsList.addAll(DBUtil_News.getNewsChannelData(pageNo, pageSize, channelName));
                    handler.sendEmptyMessage(0);
                }
            }).start();
        }
        public PullToRefreshListView getView(){
            return lvNews;
        }

    }




}
