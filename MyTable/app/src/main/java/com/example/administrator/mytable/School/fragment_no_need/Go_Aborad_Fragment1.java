package com.example.administrator.mytable.School.fragment_no_need;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytable.R;
//返回侧边栏的一些处理
public class Go_Aborad_Fragment1 extends Fragment{
    private TextView iBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.school_yt_go_aborad_head,null);
        iBack=(TextView)view.findViewById(R.id.iBack);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
