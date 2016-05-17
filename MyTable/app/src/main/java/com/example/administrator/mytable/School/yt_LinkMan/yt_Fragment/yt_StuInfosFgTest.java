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
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.RequestPackage;
import com.example.administrator.mytable.School.yt_LinkMan.adapter.yt_StuInfosAdapter;
import com.example.administrator.mytable.School.yt_LinkMan.yt_LinkMan;

import java.util.ArrayList;
import java.util.List;

/**
 *  学生的Fragment
 * Created by Administrator on 2016/1/20.
 */
public class yt_StuInfosFgTest extends Fragment implements RequestPackage.callBackRoleDetil{
    private ListView vip;
    private yt_StuInfosAdapter adapter;
    private List<StuInfos> mRoleList;
    private RequestPackage pac;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private yt_LinkMan linkManActivity;
    private StuInfos stuInfos;
//    private interface MyCallback{
//        void callback(List<>);
//    }
    private int id;
    private String role;
    private String name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pac=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());
        pac.setCallBackRoleDetil(this);
//        Bundle bundle=getArguments();
//        id=bundle.getInt("id");
//        role=bundle.getString("role");
//        name=bundle.getString("name");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.zhuanye_and_zhiwu_layout,null);
        vip= (ListView) view.findViewById(R.id.lvList);
        text2= (TextView) view.findViewById(R.id.text2);
        text3= (TextView) view.findViewById(R.id.text3);
        text4= (TextView) view.findViewById(R.id.text4);
        image2= (ImageView) view.findViewById(R.id.image2);
        image3= (ImageView) view.findViewById(R.id.image3);
        image4= (ImageView) view.findViewById(R.id.image4);
//        text3.setText(name);
//        text4.setText(role);
        text2.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
        image3.setVisibility(View.VISIBLE);
        text4.setVisibility(View.VISIBLE);
        image4.setVisibility(View.VISIBLE);
        initZhuanYeZhiWuData();
        initLisener();
        barsLisrner();/////////////////////////////////////////////////
        return view;
    }
    private void initZhuanYeZhiWuData(){
        mRoleList=new ArrayList<StuInfos>();
        adapter=new yt_StuInfosAdapter(getActivity(),mRoleList);
        vip.setAdapter(adapter);
//        pac.getCallRoleStuInfosByGet("教师");//教师
        pac.getCallRoleStuInfosByGet();//教师
    }
    private void initLisener(){
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                StuInfos stuInfos = mRoleList.get(position);
//                yt_LinkMan linkManActivity = (yt_LinkMan) yt_StuInfosFgTest.this.getActivity();

            }
        });
    }
    @Override
    public void callBackRoleDetil(List roleList) {
        mRoleList.clear();
        mRoleList.addAll(roleList);
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
    private void barsLisrner(){
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                linkManActivity.replaceFragmetBars(1,stuInfos.getMajorNO());////////////////////////////////////为什么空
            }
        });
    }


}
