package com.example.administrator.mytable.School.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class CollectionBaseAdapter extends BaseAdapter{
    private Context context;
    private List<Theme> collectEntityList;

    public CollectionBaseAdapter(Context context, List<Theme> collectEntityList) {
        this.context = context;
        this.collectEntityList = collectEntityList;
    }

    @Override
    public int getCount() {
        return collectEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView title;
        public TextView time;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Theme collectEntity=collectEntityList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.yt_collection_layout,null);
            holder.title= (TextView) convertView.findViewById(R.id.title);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(collectEntity.getTitle());
        holder.time.setText(collectEntity.getTime());
        return convertView;
    }
}
