package com.example.administrator.mytable.School.yt_LinkMan.yt_Entity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/20.
 */
public class MajorMajor {
    private int img;
    private String title;

    public MajorMajor() {
    }

    public MajorMajor(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
