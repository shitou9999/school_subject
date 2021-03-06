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
import com.example.administrator.mytable.School.Entiy.New_Net;
import com.example.administrator.mytable.School.HttpURL.HttpURL;
import com.example.administrator.mytable.School.MyApplication.MyApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 可以处理多种布局的适配器
 * Created by Administrator on 2015/12/26.
 */
public class News_BaseAdapter_Net extends BaseAdapter { //implements RequestPackage.callBack
    private Context context;
    private List<New_Net> newsList;
    private ImageLoader imageLoader;
    private MyApplication application;
    public News_BaseAdapter_Net() {
    }
    public News_BaseAdapter_Net(Context context, List<New_Net> newsList) {
        this.context = context;
        this.newsList = newsList;
        application=(MyApplication)((Activity)context).getApplication();
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
        New_Net news=newsList.get(position);
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

//        String address=news.getImg();
//        try {
//            if(address.contains("图片")){
//                String str2 = URLEncoder.encode("图片", "utf-8");
//                address.replace("图片",str2);
////                application.getImageLoader().get("http://211.86.106.63/SchoolLife/" + address, ImageLoader.getImageListener(holder.img,0,0));
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        application.getImageLoader().get("http://211.86.106.63/SchoolLife/"+address,
//                application.getImageLoader().getImageListener(holder.img,0,0));
        return convertView;
    }
}
