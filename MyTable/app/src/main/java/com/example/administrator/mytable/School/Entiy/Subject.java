package com.example.administrator.mytable.School.Entiy;

/**
 * Created by Administrator on 2015/12/31.
 */
public class Subject {
    private String title;
    private int imgRes;

    public Subject() {
    }

    public Subject(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
