package com.example.administrator.mytable.School.Entiy;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/15.
 */
public class ImgRes_Net implements Serializable{
    private String img;
    private String time;
    private String title;
    private String content;
    private int id;
    private int pagetag;
    private int pagetagflag;
    private int tag;

    public ImgRes_Net() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPagetag() {
        return pagetag;
    }

    public void setPagetag(int pagetag) {
        this.pagetag = pagetag;
    }

    public int getPagetagflag() {
        return pagetagflag;
    }

    public void setPagetagflag(int pagetagflag) {
        this.pagetagflag = pagetagflag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
