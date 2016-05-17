package com.example.administrator.mytable.School.MyApplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.yt_Apk.APKUtil;

/**
 * Created by Administrator on 2016/1/14. 自定义全局
 */
public class MyApplication extends Application {
    private RequestQueue queue;
    private ImageLoader imageLoader;
    private static MyApplication instance;
    private StuInfos stu;
    private int verCode;
    private String verName;
    public static final int MATH=1;  //数学与应用数学
    public static final int INFOR=2;//信息与计算科学
    public static final int STATISTICS=3; ////统计学
    public static final String STU="%E5%AD%A6%E7%94%9F";
    public static final String BAN="%E7%8F%AD%E9%95%BF";
    public static final String TEACHER="%E6%95%99%E5%B8%88";
    public static Bitmap bitmap;

    public static Bitmap getBitmap() {
        return bitmap;
    }
    private BitmapCache cache;

    public static void setBitmap(Bitmap bitmap) {
        MyApplication.bitmap = bitmap;
    }

    /**
     * 判断网络是否连接
     */
    public static boolean checkNetWorkConnectionState(Context context){
        ConnectivityManager connMgr=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }
/**
     * 系统构建这个的时候会调用这个方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        queue= Volley.newRequestQueue(this);
        cache=new BitmapCache();
        imageLoader=new ImageLoader(queue, cache);//创建一块缓存区域
        verCode= APKUtil.getVersionCode(this);
        verName=APKUtil.getVersionName(this);
    }
    public RequestQueue getRequestQueue(){
        return queue;
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
    private class BitmapCache implements ImageLoader.ImageCache{//内部类    缓存
        private LruCache<String,Bitmap> cache; //类似于集合,管理缓存
        public BitmapCache(){
            int cacheSize= (int) (Runtime.getRuntime().maxMemory()/1024/8);//取设备运行时最大内存的1/8作为缓存的大小
            cache=new LruCache<String,Bitmap>(cacheSize);

        }
        @Override  //请求先取缓存数据，如果取得不再访问网络
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }
        @Override //请求网络图片，先存到缓存中
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url,bitmap);
        }



    }
    public static MyApplication getInstance(){
        return instance;
    }

    public int getVerCode() {
        return verCode;
    }

    public void setVerCode(int verCode) {
        this.verCode = verCode;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public StuInfos getStu() {
        return stu;
    }

    public void setStu(StuInfos stu) {
        this.stu = stu;
    }


}
