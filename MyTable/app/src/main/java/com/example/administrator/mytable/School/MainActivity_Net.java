package com.example.administrator.mytable.School;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.Entiy.Subject_Head_Net;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.HttpURL.HttpURL;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.MyStringRequest.MyStringRequest;
import com.example.administrator.mytable.School.adapter.Head_PagerAdapter_Net;
import com.example.administrator.mytable.School.adapter.News_BaseAdapter22;
import com.example.administrator.mytable.School.db.CheckHttpUtil;
import com.example.administrator.mytable.School.db.DBManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_Net extends AppCompatActivity
        implements PullToRefreshBase.OnRefreshListener,ViewPager.OnPageChangeListener{
    private PullToRefreshListView vip;
    private News_BaseAdapter22 adapter;
    private List<Theme> newsList;
    private SlidingMenu menu;
    private LinearLayout select01;
    private LinearLayout select02;
    private LinearLayout select05;
    private LinearLayout select03;
    private LinearLayout select04;
    private LinearLayout select06;
    private ViewPager pagerVip;
    private List<Theme> imgList;
    private Head_PagerAdapter_Net pagerAdapter;
    private ImageView iReturn;
    private RadioGroup rgoup;
    private RadioGroup rGoup;
    private TextView tvLogin;
    private ImageView ivLogin;
    private LinearLayout clickMore;
    private RelativeLayout rvMore;
    private ImageView image1;
    private TextView title;
    private MyApplication app;
    private ImageView imgs;//头部
    private Subject_Head_Net HeadNet;
    private StuInfos stuInfos;
    private List<Subject_Head_Net> subjectHeadNetList;
    private SharedPreferences sp;
    private CheckHttpUtil checkHttpUtil;
    private Bitmap bitmap;
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
        app=(MyApplication)super.getApplication();
        subjectHeadNetList = new ArrayList<Subject_Head_Net>();
        HeadNet = new Subject_Head_Net();
        imgList=new ArrayList<Theme>();
        sp=getSharedPreferences("person", Context.MODE_PRIVATE);
        initView();
        initautoLoginLisener();
        initPulltoRefreshListView();
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
    }


    private void initautoLoginLisener() {
        if(checkHttpUtil.checkNetWorkConnectionState(this)){
            if(sp.getBoolean("AUTO_ISCHECK", false)==true&&sp.getBoolean("autoLogin",false)==true){  //自动登录
                String name=sp.getString("User", "");
                String pwd=sp.getString("Psw", "");
                Map<String, String> params=new HashMap<String, String>();//////
                params.put("userName", name);//和后台的参数一致
                params.put("pwd", pwd);//和后台的参数一致
                String url=HttpURL.Login;
                final MyStringRequest request=new MyStringRequest(url, params,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson=new Gson();
                                stuInfos=gson.fromJson(response, StuInfos.class); //直接返回一个类
                                sp.edit().putBoolean("autoLogin", true);////////////////////////
                                tvLogin.setText(stuInfos.getUno());
                                app.getImageLoader().get(stuInfos.getImg(),
                                        app.getImageLoader().getImageListener(ivLogin, R.mipmap.cc_default_head, R.mipmap.cc_default_head));
                                tvLogin.setClickable(false);

                                ImageRequest request=new ImageRequest(stuInfos.getImg(), new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap response) {
                                        app.setBitmap(response);
                                    }
                                },0,0,ImageView.ScaleType.CENTER,Bitmap.Config.RGB_565,new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                MyApplication.getInstance().getRequestQueue().add(request);
                                app.setStu(stuInfos);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                app.getRequestQueue().add(request);
            }else{
                Bitmap bitmap= BitmapFactory.decodeResource(MainActivity_Net.this.getResources(), R.mipmap.logo);
                app.setBitmap(bitmap);
            }
        }else{
            Bitmap bitmap= BitmapFactory.decodeResource(MainActivity_Net.this.getResources(), R.mipmap.logo);
            app.setBitmap(bitmap);
            Toast.makeText(MainActivity_Net.this, "请检查网络", Toast.LENGTH_SHORT).show();
        }
        
    }
    @Override
    protected void onStart() {
        super.onStart();
        ivLogin.setImageBitmap(MyApplication.getBitmap());
        StuInfos stu=app.getStu();
        if(stu==null){
            tvLogin.setClickable(true);
            tvLogin.setText("请登录");
        }else{
            loadHead(stu);
            tvLogin.setText(stu.getUno());
            tvLogin.setClickable(false);
        }
    }
    private void loadHead(StuInfos stu){
        String url=stu.getImg();
        MyApplication.getInstance().getImageLoader().
                get(url, ImageLoader.getImageListener(ivLogin, R.mipmap.logo, R.mipmap.logo));
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    vip.onRefreshComplete();//关闭刷新
                    break;
                case 1:
                    pagerAdapter.notifyDataSetChanged();//在这上面关闭进度条
                    break;

            }
        }
    };
    /**
     * 初始化主页刷新
     */
    private View view;
    private void initPulltoRefreshListView(){
        newsList = new ArrayList<Theme>();
        adapter=new News_BaseAdapter22(this,newsList);
        vip.setOnRefreshListener(this);
        vip.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        vip.setAdapter(adapter);
        initHeadViewAndloadData();
        vip.setRefreshing();
    }
    private void initHeadViewAndloadData(){
        view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.school_yt_img_head,null);
        pagerVip=(ViewPager)view.findViewById(R.id.pagerVip);
        clickMore=(LinearLayout)view.findViewById(R.id.clickMore);
        rvMore=(RelativeLayout)view.findViewById(R.id.rvMore);
        rGoup=(RadioGroup)view.findViewById(R.id.rGoup);
        image1=(ImageView)view.findViewById(R.id.image1);
        title=(TextView)view.findViewById(R.id.title);
        rGoup.check(R.id.i1);
        initImgView();
        loadSubjectHeadData();
        initViewPagerViewLisener();
        vip.getRefreshableView().addHeaderView(view);/////////////////////////////////
        pagerVip.addOnPageChangeListener(this);
    }
    private void initViewPagerViewLisener() {
        vip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Theme theme = newsList.get(position - 2);
                Intent intent = new Intent(MainActivity_Net.this, WebView_News_Detil_Activity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", theme);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(MainActivity_Net.this, School_yt_MoreSubjectList.class);
                startActivity(intent);
            }
        });
        rvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Net.this, School_yt_MoreSubject1.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", HeadNet);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取头部专题的数据
     */
    private void loadSubjectHeadData(){
        final String url=HttpURL.HOST+"SchoolLife/NewsRequestServlet?pageCount=1&dataType=subject";
        if(checkHttpUtil.checkNetWorkConnectionState(MainActivity_Net.this)){
            MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    DBManager dbManager=new DBManager(MainActivity_Net.this);
                    dbManager.insertData(url,response);
                    Gson gson=new Gson();
                    subjectHeadNetList=gson.fromJson(response,new TypeToken<List<Subject_Head_Net>>(){}.getType());
                    HeadNet=subjectHeadNetList.get(0);
                    title.setText(subjectHeadNetList.get(0).getSubject_title());
                    app.getImageLoader().get(HttpURL.HOST + "SchoolLife/" + subjectHeadNetList.get(0).getSubject_url(),
                            ImageLoader.getImageListener(image1, R.mipmap.banner_01, R.mipmap.banner_01));
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            app.getRequestQueue().add(request);
        }else{
            DBManager dbManager=new DBManager(MainActivity_Net.this);
            String data=dbManager.getData(url);
            if(data!=null&&!data.equals("")){
                Gson gson=new Gson();
                subjectHeadNetList=gson.fromJson(data,new TypeToken<List<Subject_Head_Net>>(){}.getType());
                title.setText(subjectHeadNetList.get(0).getSubject_title());
                app.getImageLoader().get(HttpURL.HOST + "SchoolLife/" + subjectHeadNetList.get(0).getSubject_url(),
                        ImageLoader.getImageListener(image1, R.mipmap.cc_default_news_img, R.mipmap.cc_default_news_img));
            }
        }

    }
    /**
     * 网络获取主页的列表数据
     */
    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
      boolean  status=app.checkNetWorkConnectionState(this);
        if(status){
            vip.getRefreshableView().removeHeaderView(view);
            initHeadViewAndloadData();
        }
        loadData();
    }
    private void loadData(){
        final String url=HttpURL.HOST+"SchoolLife/NewsRequestServlet?dataType=news";
        if(checkHttpUtil.checkNetWorkConnectionState(MainActivity_Net.this)){
            MyStringRequest request=new MyStringRequest(url,  new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    DBManager dbManager=new DBManager(MainActivity_Net.this);
                    dbManager.insertData(url,response);//初次请求加缓存
                    Gson gson=new Gson();
                    List<Theme> List=gson.fromJson(response,new TypeToken<List<Theme>>(){}.getType());
                    newsList.clear();
                    newsList.addAll(List);
                    handler.sendEmptyMessage(0);
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            app.getRequestQueue().add(request);
        }else{
            DBManager dbManager=new DBManager(MainActivity_Net.this);
            String data=dbManager.getData(url);
            if(data!=null&&!data.equals("")){
                Gson gson=new Gson();
                List<Theme> List=gson.fromJson(data,new TypeToken<List<Theme>>(){}.getType());
                newsList.clear();
                newsList.addAll(List);
                handler.sendEmptyMessage(0);
            }
        }

    }

    //=============================================================================================

    /**
     * 初始化头部的ViewPager,及监听
     */
    private void initImgView(){
        pagerAdapter=new Head_PagerAdapter_Net(this,imgList);
        pagerVip.setAdapter(pagerAdapter);
        pagerVip.setCurrentItem(1);
        loadImgData();
    }
    /**
     * 获取ViewPager的数据
     */
    private void loadImgData(){
        final String url=HttpURL.HOST+"SchoolLife/NewsRequestServlet?dataType=news&isTop=1";

        if(checkHttpUtil.checkNetWorkConnectionState(MainActivity_Net.this)){
            MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    DBManager dbManager=new DBManager(MainActivity_Net.this);
                    dbManager.insertData(url,response);
                    Gson gson=new Gson();
                    List<Theme> ImgList=gson.fromJson(response, new TypeToken<List<Theme>>(){}.getType());
                    imgList.clear();
                    imgList.addAll(ImgList);
                    handler.sendEmptyMessage(1);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            app.getRequestQueue().add(request);
        }else{
            DBManager dbManager=new DBManager(MainActivity_Net.this);
            String data=dbManager.getData(url);
            if(data!=null&&!data.equals("")){
                Gson gson=new Gson();
                List<Theme> ImgList=gson.fromJson(data, new TypeToken<List<Theme>>(){}.getType());
                imgList.addAll(ImgList);
                handler.sendEmptyMessage(1);
            }
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
                if(tvLogin.getText().equals("请登录")){
                    Toast.makeText(MainActivity_Net.this, "请先登录", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(MainActivity_Net.this,School_yt_SetDetail.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("infos", app.getStu());
                    bundle.putParcelable("data", bitmap);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_Net.this,School_yt_To_Login.class);
                startActivityForResult(intent,0);
            }
        });
        select01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Net.this, School_yt_To_Message.class);
                startActivity(intent);
            }
        });
        select02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Net.this, School_yt_ToGame.class);
                startActivity(intent);
            }
        });
        select03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_Net.this,School_yt_GoThink_Lead.class);
                startActivity(intent);
            }
        });
        select04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_Net.this,School_yt_SeachJob.class);
                startActivity(intent);
            }
        });
        select05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_Net.this,School_yt_ToAroad.class);
                startActivity(intent);
            }
        });
        select06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_Net.this,School_yt_ToTools.class);
                startActivity(intent);
            }
        });
    }


}
