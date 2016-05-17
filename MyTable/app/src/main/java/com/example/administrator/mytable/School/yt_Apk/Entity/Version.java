package com.example.administrator.mytable.School.yt_Apk.Entity;

/**
 * Created by Administrator on 2016/3/10.
 */
public class Version {
//    {"code":"2","mark":"","name":"1.1","url":"apk/cc_111015111056.apk","vid":1}
    private String code;
    private String mark;
    private String name;
    private String url;
    private int vid;

    public Version() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }
}
