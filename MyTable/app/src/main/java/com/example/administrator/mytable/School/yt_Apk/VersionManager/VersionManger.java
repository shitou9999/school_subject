package com.example.administrator.mytable.School.yt_Apk.VersionManager;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.yt_Apk.Entity.Version;
import com.example.administrator.mytable.School.yt_Apk.UrlUtil.UrlUtil;
import com.google.gson.Gson;

import java.io.File;

/**
 * 1.util  2.全局Myapp 3. Manger  4.权限+SD卡读写权限  5.调用
 * Created by Administrator on 2016/3/10.
 */
public class VersionManger {
    private Context context;
    private DownloadManager dm;
    private ProgressDialog dialog;
    private AlertDialog progressDialog;
    private ProgressBar mBar;
    private boolean ifcancel=false;
    private int fileSize;
    private int bytesDL;
    private long downloadId;
    public static final int GET_VERSTON_SUCCESS =1 ;
    public VersionManger(Context context){
        this.context=context;

    }
    private Version v;
    public  void getVersionInfo(final Handler handler){
        String url= UrlUtil.URL_HOST+UrlUtil.URL_APK;
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                v =gson.fromJson(response, Version.class);
                handler.sendEmptyMessage(GET_VERSTON_SUCCESS);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("jereh", error.getMessage()); //可以发消息网络超时
            }
        });
        MyApplication.getInstance().getRequestQueue().add(request);
    }
    //检查是否更新
    public boolean checkUpdate(){
        v.setVid(2); //是测试
        if(v.getVid()>MyApplication.getInstance().getVerCode()){
            return true;
        }else{
            return false;
        }

    }
    private Uri download_uri=Uri.parse("content://downloads/my_downloads");
    private DownloadChangeContentObserver downloadContentObs;

    /**
     * 更新版本
     */
    public void downloadAPK(){
        if(v!=null){
            String url=UrlUtil.URL_HOST+"/"+v.getUrl();
            File apkDir= new File(Environment.getExternalStorageDirectory().getPath()+"/xyt/apk");
            if(!apkDir.getParentFile().exists()){
                apkDir.getParentFile().mkdirs();//带s 包含在子目录中
            }else if(!apkDir.exists()){
                apkDir.mkdir();
            }
            String apkFileName=v.getUrl().substring(v.getUrl().lastIndexOf("/"));
            //获得系统服务 getSystemService
            dm= (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
//            apkDir.getPath()完整的路径
            request.setDestinationInExternalPublicDir("/xyt/apk", apkFileName);
            //用下面的显示要加权限 <uses-permission android:name="DOWNLOAD_WITHOUT_NOTIFICATION"/>
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setTitle("校园通");
            //下载完成后，发送一个广播（通知） 然后再调用系统安装程序
//            DownloadManager.ACTION_DOWNLOAD_COMPLETE
            downloadId=dm.enqueue(request);
            //注册一个广播
            IntentFilter intentFilter=new IntentFilter();
            intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            context.registerReceiver(receiver, intentFilter);
//            dialog=new ProgressDialog(context);//圆形进度条-----比较简单
//            dialog.setTitle("更新中");
//            dialog.setMessage("正在下载.....");
//            dialog.show();
            downloadContentObs=new DownloadChangeContentObserver(null);
            context.getContentResolver().
                    registerContentObserver(download_uri, true, downloadContentObs);
            showDownloadProessDialog();
        }
    }
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        //接受广播后回调该方法
        @Override
        public void onReceive(Context context, Intent intent) {
//            dialog.dismiss();
            if(!ifcancel){
                progressDialog.dismiss();
                installAPK();//自动安装
            }
        }
    };
    /**
     * 调用系统安装程序
     */
    public void installAPK(){
        String apkFileName=v.getUrl().substring(v.getUrl().lastIndexOf("/"));
        String apkPath=Environment.getExternalStorageDirectory().getPath()+"/xyt/apk";
        File apkFile = new File(apkPath,apkFileName);
        if (!apkFile.exists()) {
            return;
        }
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        context.startActivity(i);
    }
    class DownloadChangeContentObserver extends ContentObserver {
        public DownloadChangeContentObserver(Handler handler){
            super(handler);
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            queyDownloadStatus();//查询当前下载的数据
            mBar.setMax(fileSize);
            mBar.setProgress(bytesDL);
        }
    }

    /**
     * 显示实时进度的进度条
     */
    private void showDownloadProessDialog(){
        View view= LayoutInflater.from(context).inflate(R.layout.yt_progess_layout, null);
        mBar= (ProgressBar) view.findViewById(R.id.mBar);
//        mBar.setMax();
        mBar.setProgress(0); //初始值
        progressDialog=new AlertDialog.Builder(context)
                .setTitle("下载中")
                .setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dm.remove(downloadId);//下载中取消下载
                        ifcancel=true;
                        dialog.dismiss();
                    }
                })
                .show();
        progressDialog.show();
    }

    /**
     * 取下载进度数据(可以跨进程访问系统的数据)
     */
    private void queyDownloadStatus(){
        DownloadManager.Query query=new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor c=dm.query(query);
        if(c!=null&&c.moveToFirst()){
            // int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            // String title = c.getString(c.getColumnIndex(DownloadManager.COLUMN_TITLE));
            fileSize = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            bytesDL = c.getInt( c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        }
    }
    /**
     * 取消注册广播
     */
    public void clear(){

        try{
            context.unregisterReceiver(receiver);
            context.getContentResolver().unregisterContentObserver(downloadContentObs);
        }catch (Exception e){

        }
    }
}
















