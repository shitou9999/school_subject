package com.example.administrator.mytable.School;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.adapter.GameNewsPagerAdapter;
import com.example.administrator.mytable.School.main_fragment.GameNews_Fragment;

/**
 * Created by Administrator on 2015/12/30.
 */
public class School_yt_GoThink_Lead extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewpagerVip;
    private TabLayout tabs;
    private GameNewsPagerAdapter adapter;
    private String[] channelNames={"团学动态","好书推荐","师生风采","心灵分享"};
    private LinearLayout iBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_yt_go_think_lead);
        viewpagerVip=(ViewPager)super.findViewById(R.id.viewpagerVip);
        tabs=(TabLayout)super.findViewById(R.id.tabs);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        iBack.setOnClickListener(this);
        initViewPageData();
    }
    private void initViewPageData(){
        adapter=new GameNewsPagerAdapter(super.getSupportFragmentManager());
        GameNews_Fragment fragment=null;
        for(int i=0;i<channelNames.length;i++){
            fragment=GameNews_Fragment.newsInstance(channelNames[i]);
            adapter.addFragment(fragment,channelNames[i]);
        }
        viewpagerVip.setAdapter(adapter);
        tabs.setupWithViewPager(viewpagerVip);
    }

    @Override
    public void onClick(View v) {
        super.finish();
    }
}
