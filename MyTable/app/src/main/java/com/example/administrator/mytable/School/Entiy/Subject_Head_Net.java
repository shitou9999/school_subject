package com.example.administrator.mytable.School.Entiy;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/15.
 */
public class Subject_Head_Net implements Serializable{
    private String subject_date;
    private String subject_detail;
    private int subject_id;
    private String subject_title;
    private String subject_url;

    public Subject_Head_Net() {
    }

    public String getSubject_date() {
        return subject_date;
    }

    public void setSubject_date(String subject_date) {
        this.subject_date = subject_date;
    }

    public String getSubject_detail() {
        return subject_detail;
    }

    public void setSubject_detail(String subject_detail) {
        this.subject_detail = subject_detail;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public String getSubject_url() {
        return subject_url;
    }

    public void setSubject_url(String subject_url) {
        this.subject_url = subject_url;
    }
}
