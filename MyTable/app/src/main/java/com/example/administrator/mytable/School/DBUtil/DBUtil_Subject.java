package com.example.administrator.mytable.School.DBUtil;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class DBUtil_Subject {

    public static List<Subject> getSubjectData(){
        List<Subject> subjectList=new ArrayList<Subject>();
        subjectList.add(new Subject("迎新季", R.mipmap.subject1));
        return subjectList;
    }
}
