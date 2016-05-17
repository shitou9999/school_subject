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
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Class;

import java.util.List;

/**
 * 含有3个人头的适配器
 * Created by Administrator on 2016/1/20.
 */
public class yt_ClassAdapter extends BaseAdapter{
    private Context context;
    private List<Class> classList;
    public yt_ClassAdapter() {
    }
    public yt_ClassAdapter(Context context, List<Class> classList) {
        this.context = context;
        this.classList = classList;
    }
    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return classList.get(position);
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
        Class mClass=classList.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.yt_class_1man,null);
            holder.imgRes= (ImageView) convertView.findViewById(R.id.poto);
            holder.name= (TextView) convertView.findViewById(R.id.name);
//            holder.img= (CheckBox) convertView.findViewById(R.id.checkbox01);
            convertView.setTag(holder);
        }else{
           holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mClass.getClassName());
        return convertView;
    }
}
