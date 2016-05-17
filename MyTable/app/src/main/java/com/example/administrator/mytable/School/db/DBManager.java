package com.example.administrator.mytable.School.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.mytable.School.Entiy.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/2.
 */
public class DBManager {
    private DBHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * 插入缓存(新闻等缓存)
     */
    public void insertData(String url, String data) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.URL, url);
        values.put(DBHelper.DATA, data);
        values.put(DBHelper.TIME, System.currentTimeMillis());
        database.replace(DBHelper.CACHE, null, values);//////////////
//        Log.d("jereh", ret + "");
        database.close();
    }

    /**
     * 根据url获取缓存数据,
     */
    public String getData(String url) {
        String result = "";
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select  * from " + DBHelper.CACHE + " where url = ?", new String[]{url});
        if (cursor.moveToNext()) {
            result = cursor.getString(cursor.getColumnIndex(DBHelper.DATA));
        }
        cursor.close();
        database.close();
        return result;
    }

    /**
     * 插入收藏数据
     *
     * @param collectEntity
     */
    public void insertCollect(Theme collectEntity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = " insert into collect(title,times,content) values(?,?,?) ";
        db.execSQL(sql, new String[]{collectEntity.getTitle(), collectEntity.getTime(), collectEntity.getContent()});
        db.close();
    }

    /**
     * 取收藏数据
     */
    public List<Theme> searchCollectEntity() {
        List<Theme> collectEntityList = new ArrayList<Theme>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "select * from collect ";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex("collect_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String times = cursor.getString(cursor.getColumnIndex("times"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            collectEntityList.add(new Theme(title,times,content));
        }
        cursor.close();
        database.close();
        return collectEntityList;
    }
    /**
     * 删除某条数据
     */
    public void deleteCollect(Theme collectEntity){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("collect", "title=?", new String[]{collectEntity.getTitle()});
        db.close();
    }
    /**
     * 判断某一条数据是否存在
     */
    public boolean isExists(String title){
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        String sql = "select * from collect where title=?";
        Cursor cursor=database.rawQuery(sql,new String[]{title});
        if(cursor.moveToNext()){
            return true;
        }else{
            return false;
        }
    }
}

