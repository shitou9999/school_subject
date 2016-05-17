package com.example.administrator.mytable.School.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/2.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "djk";
    public static final int version = 6;
    public static final String CACHE = "cache";
    public static final String URL = "url";
    public static final String DATA = "data";
    public static final String TIME = "time";
    public static final String COLLECT="collect";
    public static final String COLLECT_ID="collect_id";
    public static final String TITLET = "title";
    public static final String TIMES = "times"; //收藏的时间
    public static final String CONTENT = "content";

    public DBHelper(Context context) {
        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "
                + CACHE + " ("
                + URL + " TEXT PRIMARY KEY, "
                + TIME + " TEXT, "
                + DATA + " TEXT)";
        db.execSQL(sql);

        StringBuffer sb=new StringBuffer();
        sb.append(" create table if not exists ");
        sb.append(COLLECT);
        sb.append("(");
        sb.append(COLLECT_ID+" integer not null primary key autoincrement,");
        sb.append(TITLET+" varchar(20) not null,");
        sb.append(TIMES+" varchar(20), ");
        sb.append(CONTENT+" varchar(500)");
        sb.append(" )");
        db.execSQL(sb.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists " +CACHE;
        String sql2="drop table if exists "+ COLLECT;
        db.execSQL(sql);
        db.execSQL(sql2);
        onCreate(db);
    }
}
