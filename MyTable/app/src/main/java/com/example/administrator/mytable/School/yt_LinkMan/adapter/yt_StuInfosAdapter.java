package com.example.administrator.mytable.School.yt_LinkMan.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.StuInfos;

import java.util.List;

/**
 * 显示学生列表的适配器
 * Created by Administrator on 2016/1/20.
 */
public class yt_StuInfosAdapter extends BaseAdapter{
    private Context context;
    private List<StuInfos> mStuInfosList;
    public yt_StuInfosAdapter() {
    }
    public yt_StuInfosAdapter(Context context, List<StuInfos> mStuInfosList) {
        this.context = context;
        this.mStuInfosList = mStuInfosList;
    }
    @Override
    public int getCount() {

        Log.d("jereh","getCount");
        return mStuInfosList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStuInfosList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        public ImageView imgRes;
        public TextView name;
//        private CheckBox img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StuInfos mStuInfos=mStuInfosList.get(position);
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
        holder.name.setText(mStuInfos.getStuName());
        return convertView;
    }
}
