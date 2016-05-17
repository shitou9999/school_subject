package com.example.administrator.mytable.School;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.db.CheckHttpUtil;
import com.example.administrator.mytable.School.db.DBManager;

public class WebView_News_Detil_Activity2 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private TextView title;
    private TextView date;
    private RelativeLayout iBack;
    private WebView webView;
    private Theme theme;
    private CheckBox checkBox;
    private SharedPreferences sp;
    private boolean setCollection;
    private MyApplication app;
    private DBManager db;
    private CheckHttpUtil checkHttpUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_news__detil_);
        iBack= (RelativeLayout) super.findViewById(R.id.iBack);
        title=(TextView)super.findViewById(R.id.title);
        date=(TextView)super.findViewById(R.id.date);
        webView= (WebView) super.findViewById(R.id.webView);
        checkBox= (CheckBox) super.findViewById(R.id.checkBox);
        sp=getSharedPreferences("person", Context.MODE_PRIVATE);
        app=MyApplication.getInstance();
        initView();
        initViewLisener();
        initWebView();
        db=new DBManager(this);
        if(checkHttpUtil.checkNetWorkConnectionState(this)){
            if(sp.getBoolean("autoLogin",false)){ //判断是否登录
                checkBox.setVisibility(View.VISIBLE);
            }else{
                checkBox.setVisibility(View.GONE);
            }
        }else{
            checkBox.setVisibility(View.GONE);
        }
        if(db.isExists(theme.getTitle())){ //数据库有显示取消收藏
            checkBox.setText("取消收藏");
            checkBox.setChecked(true);
        }else{
            checkBox.setText("收藏");
            checkBox.setChecked(false);
        }
        checkBox.setOnCheckedChangeListener(this);
    }
    private void initWebView() {
        final WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//是否支持JavaScrip
        webSettings.setSupportZoom(true);//是否支持缩放
        webSettings.setBuiltInZoomControls(true);//是否显示控制按钮
        webSettings.setDefaultTextEncodingName("utf-8");//设置解码时默认编码
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override //页面启动的时候
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        webView.loadDataWithBaseURL(null, theme.getContent(), "text/html", "utf-8", null);
    }

    private void initView(){
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        theme= (Theme) bundle.get("data");
        title.setText(theme.getTitle());
        date.setText(theme.getTime());
    }

    private void initViewLisener() {
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView_News_Detil_Activity2.this.finish();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            if(!db.isExists(theme.getTitle())){ //如果数据库不存在，显示取消收藏，把数据插入数据库
                checkBox.setText("取消收藏");
                db.insertCollect(new Theme(theme.getTitle(), theme.getTime(), theme.getContent()));
                Toast.makeText(WebView_News_Detil_Activity2.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        }else{
            if(db.isExists(theme.getTitle())){
                checkBox.setText("收藏");
                db.deleteCollect(new Theme(theme.getTitle(), theme.getTime(), theme.getContent()));
                Toast.makeText(WebView_News_Detil_Activity2.this, "取消成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
