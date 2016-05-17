package com.example.administrator.mytable.School.fragment_no_need;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.DBUtil.DBUtil_News;
import com.example.administrator.mytable.School.adapter.ChannelPagerAdapter;

/**
 * Created by Administrator on 2015/12/26.
 */
public class Game_Activity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private ViewPager gameVip;
    private ChannelPagerAdapter gameListPagerAdapter;
    private RadioGroup rgoup;
    private TextView iBack;
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.school_yt_go_game);
        gameVip=(ViewPager)super.findViewById(R.id.gameVip);
        rgoup=(RadioGroup)super.findViewById(R.id.rgoup);
        iBack=(TextView)super.findViewById(R.id.iBack);
        initView();
    }
    private void initView(){
        initViewPager();
        rgoup.check(R.id.rb1);
        rgoup.setOnCheckedChangeListener(this);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewPager(){
        gameListPagerAdapter=new ChannelPagerAdapter(this,DBUtil_News.getChannelData());
        gameVip.setAdapter(gameListPagerAdapter);
        gameVip.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//翻页监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rgoup.check(R.id.rb1);
                        break;
                    case 1:
                        rgoup.check(R.id.rb2);
                        break;
                    case 2:
                        rgoup.check(R.id.rb3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){
            switch (checkedId) {
                case R.id.rb1:
                    gameVip.setCurrentItem(0);
                    break;
                case R.id.rb2:
                    gameVip.setCurrentItem(1);
                    break;
                case R.id.rb3:
                    gameVip.setCurrentItem(2);
                    break;
            }
        }
}
