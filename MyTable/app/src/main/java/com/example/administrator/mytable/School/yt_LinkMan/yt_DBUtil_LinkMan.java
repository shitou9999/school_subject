package com.example.administrator.mytable.School.yt_LinkMan;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.MajorMajor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
public class yt_DBUtil_LinkMan {
    public static List<MajorMajor> getMajorMajorData(){
        List<MajorMajor> majorMajorList=new ArrayList<MajorMajor>();
        majorMajorList.add(new MajorMajor(R.mipmap.zhunye_03,"专业"));
        majorMajorList.add(new MajorMajor(R.mipmap.zhiwu_07,"职务"));
        return majorMajorList;
    }
}
