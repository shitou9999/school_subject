package com.example.administrator.mytable.School.yt_WebView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.administrator.mytable.R;

public class ToYouthLeagueActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private LinearLayout iBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_yantai_main);
        webView= (WebView) super.findViewById(R.id.vip);
        progressBar= (ProgressBar) super.findViewById(R.id.progressBar);
        iBack= (LinearLayout) super.findViewById(R.id.iBack);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initWebView();
    }
    private void initWebView() {
        webView.loadUrl("http://youth.ytu.edu.cn/");
        //2.设置webview的参数
        final WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//是否支持JavaScrip
        webSettings.setSupportZoom(true);//是否支持缩放
        webSettings.setBuiltInZoomControls(true);//是否显示控制按钮
        webSettings.setDefaultTextEncodingName("utf-8");//设置解码时默认编码
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webView.setWebViewClient(new WebViewClient() { //主要处理解析，渲染网页等浏览器做的事情
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override //页面启动的时候
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){ //判断是否回退键按下
            webView.goBack();
            return true;
        }else{
            finish();
            return false;//执行完事件，不吸收掉
        }
    }
}
