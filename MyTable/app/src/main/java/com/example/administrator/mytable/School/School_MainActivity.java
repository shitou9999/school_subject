package com.example.administrator.mytable.School;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.DBUtil.DBUtil_News;
import com.example.administrator.mytable.School.Entiy.News;
import com.example.administrator.mytable.School.adapter.Head_PagerAdapter;
import com.example.administrator.mytable.School.adapter.News_BaseAdapter;
import com.example.administrator.mytable.School.adapter.News_BaseAdapter_Net;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class School_MainActivity extends AppCompatActivity
        implements PullToRefreshBase.OnRefreshListener2,ViewPager.OnPageChangeListener{
    private PullToRefreshListView vip;
    private News_BaseAdapter adapter;
    private List<News> newsList;
    private int pageNo;
    private int pageSize;
    private SlidingMenu menu;
    private LinearLayout select01;
    private LinearLayout select02;
    private LinearLayout select05;
    private LinearLayout select03;
    private LinearLayout select04;
    private LinearLayout select06;
    private ViewPager pagerVip;
    private List<ImageView> imgList;
    private Head_PagerAdapter pagerAdapter;
    private ImageView iReturn;
    private RadioGroup rgoup;
    private RadioGroup rGoup;
    private TextView tvLogin;
    private ImageView ivLogin;
    private LinearLayout clickMore;
    private RelativeLayout rvMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_main);
        vip=(PullToRefreshListView)super.findViewById(R.id.vip);
        rgoup=(RadioGroup)super.findViewById(R.id.rgoup);
        iReturn=(ImageView)super.findViewById(R.id.iReturn);
        iReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });
        initView();
    }

    /**
     * 初始化侧边栏
     */
    private void initView(){
        menu=new SlidingMenu(this);
        menu.setMenu(R.layout.school_yt_sliding);
        menu.setShadowWidth(30);
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffset(350);
        menu.setTouchModeAbove(SlidingMenu.LEFT);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setFadeDegree(0.35f);
        ColorDrawable colorDrawable=new ColorDrawable(Color.rgb(120, 120, 120));
        tvLogin=(TextView)super.findViewById(R.id.tvLogin);
        ivLogin=(ImageView)super.findViewById(R.id.ivLogin);
//        menu.setShadowDrawable(R.drawable.shadow);
        select01=(LinearLayout)super.findViewById(R.id.select01);
        select02=(LinearLayout)super.findViewById(R.id.select02);
        select03=(LinearLayout)super.findViewById(R.id.select03);
        select04=(LinearLayout)super.findViewById(R.id.select04);
        select05=(LinearLayout)super.findViewById(R.id.select05);
        select06=(LinearLayout)super.findViewById(R.id.select06);
        setSelectSlidingOnclik();//侧边栏选择监听
        initPulltoRefreshListView();
    }

    /**
     * 初始化主页刷新
     */
    private void initPulltoRefreshListView(){
        newsList=new ArrayList<News>();
        adapter=new News_BaseAdapter(this,newsList);
        vip.setOnRefreshListener(this);
        vip.setMode(PullToRefreshBase.Mode.BOTH);
        vip.setAdapter(adapter);
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.school_yt_img_head,null);
        pagerVip=(ViewPager)view.findViewById(R.id.pagerVip);
        clickMore=(LinearLayout)view.findViewById(R.id.clickMore);
        rvMore=(RelativeLayout)view.findViewById(R.id.rvMore);
        rGoup=(RadioGroup)view.findViewById(R.id.rGoup);
        rGoup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.i1:
                        pagerVip.setCurrentItem(0);
                        break;
                    case R.id.i2:
                        pagerVip.setCurrentItem(1);
                        break;
                }
            }
        });
        clickMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_MainActivity.this, School_yt_MoreSubject.class);
                startActivity(intent);
            }
        });
        rvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_MoreSubjectList.class);
                startActivity(intent);
            }
        });
        pagerVip.addOnPageChangeListener(this);
        vip.getRefreshableView().addHeaderView(view);
        initImgView();
        vip.setRefreshing();//自动刷新，，需要改下源码
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    vip.onRefreshComplete();
                    break;
            }
        }
    };
    private void load(){
        newsList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsList.addAll(DBUtil_News.getNewsData(1, 20));
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    private void loadMore(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsList.addAll(DBUtil_News.getNewsData(pageNo, 20));
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pageNo=1;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }
        }, 1000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
         if(pageNo==6){
             Toast.makeText(this,"数据加载完毕",Toast.LENGTH_LONG).show();
             vip.onRefreshComplete();
         }else{
             handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     pageNo++;
                     loadMore();
                 }
             }, 2000);
         }
    }
    //=============================================================================================

    /**
     * 初始化头部的ViewPager,及监听
     */
    private void initImgView(){
        loadImgResData();
        pagerAdapter=new Head_PagerAdapter(this,imgList);
        pagerVip.setAdapter(pagerAdapter);
        pagerVip.setCurrentItem(1);
    }
    private void loadImgResData(){
        imgList=new ArrayList<ImageView>();
        int[] imgs=DBUtil_News.getImgRes();
        ImageView img=null;
        for(int i=0;i<imgs.length;i++){
            img=new ImageView(this);
            img.setImageResource(imgs[i]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            imgList.add(img);
        }

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rGoup.check(R.id.i1);
                break;
            case 1:
                rGoup.check(R.id.i2);
                break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    //=======================================================================================
    /**
     * 侧边栏选项监听
     */
    private void setSelectSlidingOnclik(){
        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_SetDetail.class);
                startActivity(intent);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_To_Login.class);
                startActivity(intent);
            }
        });
//        select01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(School_MainActivity.this, School_yt_To_Message.class);
//                startActivity(intent);
//            }
//        });
        select02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_MainActivity.this, School_yt_ToGame.class);
                startActivity(intent);
            }
        });
        select03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_GoThink_Lead.class);
                startActivity(intent);
            }
        });
        select04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_SeachJob.class);
                startActivity(intent);
            }
        });
        select05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_ToAroad.class);
                startActivity(intent);
            }
        });
        select06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_MainActivity.this,School_yt_ToTools.class);
                startActivity(intent);
            }
        });
    }
}
