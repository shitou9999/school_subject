package com.example.administrator.mytable.School.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.New_Net;
import com.example.administrator.mytable.School.Entiy.News;
import com.example.administrator.mytable.School.MyApplication.MyApplication;

import java.util.List;

/**
 * 可以处理多种布局的适配器
 * Created by Administrator on 2015/12/26.
 */
public class News_BaseAdapter extends BaseAdapter{
    private Context context;
    private List<News> newsList;
    private ImageLoader imageLoader;
    public News_BaseAdapter() {
    }
    public News_BaseAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
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
        News news=newsList.get(position);
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
        holder.sbTitle.setText(news.getTime());
        holder.img.setImageResource(news.getImgRes());
        return convertView;
    }
}
