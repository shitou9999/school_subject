package com.example.administrator.mytable.School.main_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.mytable.R;

//返回侧边栏的一些处理
public class Head_Fragment extends Fragment{
    private LinearLayout iBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.school_yt_go_aborad_head,null);
        iBack=(LinearLayout)view.findViewById(R.id.iBack);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });
        return view;
    }
}
