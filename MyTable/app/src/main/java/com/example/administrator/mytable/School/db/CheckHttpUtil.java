package com.example.administrator.mytable.School.db;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/3/2.
 */
public class CheckHttpUtil {
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
}
