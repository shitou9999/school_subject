package com.example.administrator.mytable.School.Entiy;

/**
 * Created by Administrator on 2015/12/26.
 */
public class News {
    private String title;
    private String time;
    private int imgRes;

    public News() {
    }

    public News(String title, String time, int imgRes) {
        this.title = title;
        this.time = time;
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
