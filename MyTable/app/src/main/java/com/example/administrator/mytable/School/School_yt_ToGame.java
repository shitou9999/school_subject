package com.example.administrator.mytable.School;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.adapter.GameNewsPagerAdapter;
import com.example.administrator.mytable.School.main_fragment.GameNews_Fragment;

public class School_yt_ToGame extends AppCompatActivity {
    private ViewPager viewpagerVip;
    private GameNewsPagerAdapter adapter;
    private TabLayout tabs;
    private String[] channelNames={"通知公告","校园新闻","素质拓展"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt__to_game);
        viewpagerVip=(ViewPager)super.findViewById(R.id.viewpagerVip);
        tabs=(TabLayout)super.findViewById(R.id.tabs);
        initView();

    }
    private void initView(){
        adapter=new GameNewsPagerAdapter(super.getSupportFragmentManager());
        GameNews_Fragment fragment=null;
        for(int i=0;i<channelNames.length;i++){
            fragment=GameNews_Fragment.newsInstance(channelNames[i]);
            adapter.addFragment(fragment,channelNames[i]);
        }
        viewpagerVip.setAdapter(adapter);
        tabs.setupWithViewPager(viewpagerVip);
    }
}
