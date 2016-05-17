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
import com.example.administrator.mytable.School.yt_LinkMan.adapter.yt_MajorAdapter;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Major;
import com.example.administrator.mytable.School.yt_LinkMan.yt_LinkMan;

import java.util.ArrayList;
import java.util.List;

/**
 * 专业的Fragment
 * Created by Administrator on 2016/1/20.
 */
public class yt_MajorFg extends Fragment
        implements RequestPackage.callBackMajorAll{
    private ListView vip;
    private yt_MajorAdapter adapter;///////////////////////////////////////
    private List<Major> mMajorList;
    private RequestPackage pac;
    private TextView text1;
    private TextView text2;
    private ImageView image2;
    private Major major;
    private yt_LinkMan linkManActivity;
//        private interface MyCallback{
//        void callback(List<>);
//    }
    private String role;
    private String name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pac=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());
        pac.setCallBackMajorAll(this);
        Bundle bundle=getArguments();
        role=bundle.getString("role");
        name=bundle.getString("name");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.zhuanye_and_zhiwu_layout,null);
        vip= (ListView) view.findViewById(R.id.lvList);
        text1= (TextView) view.findViewById(R.id.text1);
        text2= (TextView) view.findViewById(R.id.text2);
        image2= (ImageView) view.findViewById(R.id.image2);
        text2.setText(name);
        text2.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        barLisener();
        initZhuanYeZhiWuData();
        initLisener();
        return view;
    }

    private void initZhuanYeZhiWuData(){
        mMajorList=new ArrayList<Major>();
        adapter=new yt_MajorAdapter(getActivity(),mMajorList);
        vip.setAdapter(adapter);
        //load();
        pac.getMajor();
    }
    private void initLisener(){
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                major = mMajorList.get(position);
                linkManActivity = (yt_LinkMan) yt_MajorFg.this.getActivity();
                linkManActivity.replaceFragmet(1, major.getId(),"",major.getName());//统计学
            }
        });
    }
    @Override
    public void callMajorAll(List list) {
        mMajorList.clear();
        mMajorList.addAll(list);
        handler.sendEmptyMessage(0);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private void barLisener(){
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkManActivity.replaceFragmet(0,0,"","");
            }
        });
    }
}
