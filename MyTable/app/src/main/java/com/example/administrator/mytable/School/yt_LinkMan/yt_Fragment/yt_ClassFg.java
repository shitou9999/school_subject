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
import com.example.administrator.mytable.School.yt_LinkMan.adapter.yt_ClassAdapter;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Class;
import com.example.administrator.mytable.School.yt_LinkMan.yt_LinkMan;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级的Fragment
 * Created by Administrator on 2016/1/21.
 */
public class yt_ClassFg extends Fragment implements RequestPackage.callMath{
    private ListView vip;
    private yt_ClassAdapter adapter;
    private List<Class> mClassList;
    private RequestPackage pac;
    private TextView text2;
    private TextView text3;
    private ImageView image2;
    private ImageView image3;
    private Class mclss;
    private yt_LinkMan linkManActivity;
//    private interface MyCallback{
//        void callback(List<>);
//    }
    private int id;
    private String role;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pac=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());
        pac.setcallMath(this);
        Bundle bundle=getArguments();
        id=bundle.getInt("id");
        role=bundle.getString("role");//统计学
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.zhuanye_and_zhiwu_layout, null);
        vip= (ListView) view.findViewById(R.id.lvList);
        text2= (TextView) view.findViewById(R.id.text2);
        text3= (TextView) view.findViewById(R.id.text3);
        image2= (ImageView) view.findViewById(R.id.image2);
        image3= (ImageView) view.findViewById(R.id.image3);
        text3.setText(role);
        text2.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
        image3.setVisibility(View.VISIBLE);
        initZhuanYeZhiWuData();
        initLisener();
        return view;
    }
    private void initZhuanYeZhiWuData(){
        mClassList=new ArrayList<Class>();
        adapter=new yt_ClassAdapter(getActivity(), mClassList);
        vip.setAdapter(adapter);
        pac.getCallMathByGet(id);
    }
    private void initLisener(){
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mclss = mClassList.get(position);
                linkManActivity = (yt_LinkMan) yt_ClassFg.this.getActivity();
                linkManActivity.replaceFragmet(3, mclss.getId(), role, mclss.getClassName());
            }
        });
    }
    @Override
    public void callMath(List classList) {
        mClassList.clear();
        mClassList.addAll(classList);
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

}
