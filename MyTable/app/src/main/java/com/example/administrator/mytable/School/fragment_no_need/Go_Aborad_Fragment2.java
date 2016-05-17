package com.example.administrator.mytable.School.fragment_no_need;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.administrator.mytable.R;
// 负责选项的功能区
public class Go_Aborad_Fragment2 extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rgoup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.school_go_aborad_no_need,null);
        rgoup=(RadioGroup)view.findViewById(R.id.rgoup);
        rgoup.setOnCheckedChangeListener(this);
        return view;
    }
    //**********************************************************************************************
    private OnGoAborad onGoAborad;
    public interface OnGoAborad{
        void OnGoAborad(int flag);
    }
    public void setOnGoAboradListener(OnGoAborad onGoAborad){
        this.onGoAborad=onGoAborad;
    }
    //**********************************************************************************************
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb1:
                onGoAborad.OnGoAborad(0);
                break;
            case R.id.rb2:
                onGoAborad.OnGoAborad(1);
                break;
            case R.id.rb3:
                onGoAborad.OnGoAborad(2);
                break;
        }
    }
}
