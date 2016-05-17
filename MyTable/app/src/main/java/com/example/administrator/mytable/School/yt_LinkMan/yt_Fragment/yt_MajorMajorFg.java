package com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.RequestPackage;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.MajorMajor;
import com.example.administrator.mytable.School.yt_LinkMan.yt_DBUtil_LinkMan;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Role;
import com.example.administrator.mytable.School.yt_LinkMan.yt_LinkMan;
import com.example.administrator.mytable.School.yt_LinkMan.adapter.yt_LinkManAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  专业和职务的Fragment
 * Created by Administrator on 2016/1/20.
 */
public class yt_MajorMajorFg extends Fragment {
    private ListView vip;
    private yt_LinkManAdapter adapter;
    private List<MajorMajor> majorMajorList;
    private RequestPackage pac;
    private int id;
    private TextView text1;
    private yt_LinkMan linkManActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.zhuanye_and_zhiwu_layout, null);
        vip= (ListView) view.findViewById(R.id.lvList);
        text1= (TextView) view.findViewById(R.id.text1);
        initZhuanYeZhiWuData();
        initLisener();
        return view;
    }
    private void initZhuanYeZhiWuData(){
        majorMajorList=new ArrayList<MajorMajor>();
        majorMajorList.addAll(yt_DBUtil_LinkMan.getMajorMajorData());
        adapter=new yt_LinkManAdapter(getActivity(),majorMajorList);
        vip.setAdapter(adapter);
    }
    private void initLisener() {
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    linkManActivity = (yt_LinkMan) yt_MajorMajorFg.this.getActivity();
                    linkManActivity.replaceFragmet(2, 0, "专业", "专业");
                }
                if (position == 1) {
                    linkManActivity = (yt_LinkMan) yt_MajorMajorFg.this.getActivity();
                    linkManActivity.replaceFragmet(5, 0, "职务", "职务");
                }
            }
        });
    }
}
