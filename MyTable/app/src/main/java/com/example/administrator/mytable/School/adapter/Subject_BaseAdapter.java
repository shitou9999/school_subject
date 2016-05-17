package com.example.administrator.mytable.School.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Subject;

import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class Subject_BaseAdapter extends BaseAdapter{
    private Context context;
    private List<Subject> subjectList;

    public Subject_BaseAdapter() {
    }

    public Subject_BaseAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Object getItem(int position) {
        return subjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public TextView titles;
        public ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Subject subject=subjectList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).
                    inflate(R.layout.activity_school_yt_more_subject_layout,null);
            holder.img=(ImageView)convertView.findViewById(R.id.image);
            holder.titles=(TextView)convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.titles.setText(subject.getTitle());
        holder.img.setImageResource(subject.getImgRes());
        return convertView;
    }
}
