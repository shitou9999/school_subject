package com.example.administrator.mytable.School.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.HttpURL.HttpURL;
import com.example.administrator.mytable.School.MyApplication.MyApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 可以处理多种布局的适配器
 * Created by Administrator on 2015/12/26.
 */
public class News_BaseAdapter22 extends BaseAdapter{
    private Context context;
    private List<Theme> newsList;
    private ImageLoader imageLoader;
    private MyApplication application;
    public News_BaseAdapter22() {
    }
    public News_BaseAdapter22(Context context, List<Theme> newsList) {
        this.context = context;
        this.newsList = newsList;
        application= (MyApplication) ((Activity)context).getApplication();
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView title;
        public TextView sbTitle;
        public ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Theme news=newsList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.school_yt_news,null);
            holder.title=(TextView)convertView.findViewById(R.id.text1);
            holder.sbTitle=(TextView)convertView.findViewById(R.id.text2);
            holder.img=(ImageView)convertView.findViewById(R.id.image1);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText(news.getTitle());
        holder.sbTitle.setText(Html.fromHtml(news.getTime()));
        String url=news.getImg();
        String sss="";
        String ss="";
        int a =url.lastIndexOf("/");
        if (a != 0) {
            sss=url.substring(0,a+1);
            ss = url.substring(a+1);
            try {
                String aa= URLEncoder.encode(ss, "utf-8");
                url=sss+ URLEncoder.encode(ss, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else {
            url=url;
        }
        application.getImageLoader().get(HttpURL.HOST+"SchoolLife/" + url, ImageLoader.getImageListener(
                holder.img, 0, R.mipmap.cc_default_news_img
        ));
        return convertView;
    }
}
