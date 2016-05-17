package com.example.administrator.mytable.School.DBUtil;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/26.
 */
public class DBUtil_News {
    public static List<News> getNewsData(int pageNo,int pageSize){
        List<News> newsList=new ArrayList<News>();
        for(int i=0;i<pageSize;i++){
            if(i%5==0){
                newsList.add(new News("学校新闻","这是时间显示区", R.mipmap.s003));
            }else{
                newsList.add(new News("学校新闻","这是时间显示区", R.mipmap.s004));
            }
        }
        return newsList;
    }

    public static List<News> getNewsChannelData(int pageNo,int pageSize,String channel){
        List<News> newsList=new ArrayList<>();
        for(int i=0;i<pageSize;i++){
            if(i%5==0){
                newsList.add(new News("学校新闻","这是时间显示区", R.mipmap.s003));
            }else{
                newsList.add(new News("学校新闻","这是时间显示区", R.mipmap.s004));
            }
        }
        return newsList;
    }
    public static int[] getImgRes(){
        return new int[]{R.mipmap.s001,R.mipmap.s002};
    }
    public static List<String> getChannelData(){
        List<String> stringList=new ArrayList<String>();
        stringList.add("通知公告");
        stringList.add("校园新闻");
        stringList.add("素质拓展");
        return stringList;
    }
}
