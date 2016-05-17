package com.example.administrator.mytable.School;

import android.content.Intent;
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
import com.example.administrator.mytable.School.db.DBManager;

public class WebView_News_Detil_Activity3_NoNeed extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {
    private TextView title;
    private TextView date;
    private WebView webView;
    private Bundle bundle;
    private RelativeLayout iBack;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_news__detil_);
        iBack= (RelativeLayout) super.findViewById(R.id.iBack);
        title=(TextView)super.findViewById(R.id.title);
        date=(TextView)super.findViewById(R.id.date);
        webView= (WebView) super.findViewById(R.id.webView);
        checkBox= (CheckBox) super.findViewById(R.id.checkBox);
        initView();
        initViewLisener();
        initWebView();
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
        webView.loadDataWithBaseURL(null, bundle.getString("content"), "text/html", "utf-8", null);
    }

    private void initView(){
        Intent intent=super.getIntent();
        bundle=intent.getExtras();
        title.setText(bundle.getString("name"));
        date.setText(bundle.getString("time"));
    }

    private void initViewLisener() {
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView_News_Detil_Activity3_NoNeed.this.finish();
            }
        });
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            checkBox.setText("取消收藏");
            Toast.makeText(WebView_News_Detil_Activity3_NoNeed.this, "收藏成功", Toast.LENGTH_SHORT).show();
            DBManager db=new DBManager(this);
            db.insertCollect(new Theme(bundle.getString("name"),bundle.getString("time"),bundle.getString("content")));
        }else{
            checkBox.setText("收藏");
            Toast.makeText(WebView_News_Detil_Activity3_NoNeed.this, "取消成功", Toast.LENGTH_SHORT).show();
            DBManager db=new DBManager(this);
            db.deleteCollect(new Theme(bundle.getString("name"),bundle.getString("time"),bundle.getString("content")));
        }
    }
}
