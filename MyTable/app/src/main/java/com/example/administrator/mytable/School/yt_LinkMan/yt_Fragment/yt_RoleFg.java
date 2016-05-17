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
import com.example.administrator.mytable.School.yt_LinkMan.adapter.yt_RoleAdapter;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Role;
import com.example.administrator.mytable.School.yt_LinkMan.yt_LinkMan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/22.
 */
public class yt_RoleFg extends Fragment implements RequestPackage.callBackRoleAll{
    private ListView vip;
    private yt_RoleAdapter adapter;///////////////////////////////////////
    private List<Role> mRoleList;
    private RequestPackage pac;
    //    private interface MyCallback{
//        void callback(List<>);
//    }
    private ImageView image2;
    private TextView text2;
    private String role;
    private String name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pac=new RequestPackage((MyApplication) getActivity().getApplication(),getActivity());
        pac.setcallBackRoleAll(this);
        Bundle bundle=getArguments();
        role=bundle.getString("role");//职务
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.zhuanye_and_zhiwu_layout, null);
        vip= (ListView) view.findViewById(R.id.lvList);
        text2= (TextView) view.findViewById(R.id.text2);
        image2= (ImageView) view.findViewById(R.id.image2);
        text2.setText(role);//职务
        text2.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        initZhuanYeZhiWuData();
        initLisener();
        return view;
    }
    private void initZhuanYeZhiWuData(){
        mRoleList=new ArrayList<Role>();
        adapter=new yt_RoleAdapter(getActivity(),mRoleList);
        vip.setAdapter(adapter);
        pac.getRoleAllByGet(role);//职务
    }

    private void initLisener(){
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Role roles = mRoleList.get(position);
                yt_LinkMan linkManActivity = (yt_LinkMan) yt_RoleFg.this.getActivity();
                linkManActivity.replaceFragmet(4,0,role,roles.getRole());////role.getRole()教师
            }
        });
    }
    @Override
    public void callBackRoleAll(List roleList) {
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

}
