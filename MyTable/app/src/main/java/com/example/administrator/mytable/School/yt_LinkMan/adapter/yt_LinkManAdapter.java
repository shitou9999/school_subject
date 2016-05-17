package com.example.administrator.mytable.School.yt_LinkMan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.MajorMajor;

import java.util.List;

/**
 * 含有专业和职务的适配器
 * Created by Administrator on 2016/1/20.
 */
public class yt_LinkManAdapter extends BaseAdapter{
    private Context context;
    private List<MajorMajor> majorMajorList;
    public yt_LinkManAdapter() {
    }
    public yt_LinkManAdapter(Context context, List<MajorMajor> majorMajorList) {
        this.context = context;
        this.majorMajorList = majorMajorList;
    }
    @Override
    public int getCount() {
        return majorMajorList.size();
    }

    @Override
    public Object getItem(int position) {
        return majorMajorList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public ImageView imgRes;
        public TextView name;
        public CheckBox img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MajorMajor majorMajor=majorMajorList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.yt_linkman_layout,null);
            holder.imgRes= (ImageView) convertView.findViewById(R.id.zhuanYe);
            holder.name= (TextView) convertView.findViewById(R.id.zhuanYes);
            convertView.setTag(holder);
        }else{
           holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(majorMajor.getTitle());
        holder.imgRes.setImageResource(majorMajor.getImg());
        return convertView;
    }
}
