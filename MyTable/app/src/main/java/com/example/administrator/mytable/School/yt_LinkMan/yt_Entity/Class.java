package com.example.administrator.mytable.School.yt_LinkMan.yt_Entity;

/**
 * Created by Administrator on 2016/1/20.
 */
public class Class {
    private int id;
    private int majorId;
    private String className;
    private String majorIdEncod;
    private String majorName;
    private int yearLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajorIdEncod() {
        return majorIdEncod;
    }

    public void setMajorIdEncod(String majorIdEncod) {
        this.majorIdEncod = majorIdEncod;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
}
