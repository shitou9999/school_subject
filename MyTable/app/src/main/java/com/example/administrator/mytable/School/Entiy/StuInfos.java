package com.example.administrator.mytable.School.Entiy;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/18.
 */
public class StuInfos implements Serializable{
    private int classNO;
    private String className;
    private String img;
    private int isRead;
    private int majorNO;
    private String majorName;
    private String pwd;
    private String role;
    private int smsright;
    private String stuName;
    private String uno;
    private String year;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public StuInfos() {
    }

    public int getClassNO() {
        return classNO;
    }

    public void setClassNO(int classNO) {
        this.classNO = classNO;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getMajorNO() {
        return majorNO;
    }

    public void setMajorNO(int majorNO) {
        this.majorNO = majorNO;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSmsright() {
        return smsright;
    }

    public void setSmsright(int smsright) {
        this.smsright = smsright;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
