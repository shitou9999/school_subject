package com.example.administrator.mytable.School.yt_LinkMan;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_ClassFg;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_MajorFg;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_MajorMajorFg;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_RoleFg;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_StuInfosFg;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_StuInfosFgTest;

public class yt_LinkMan extends AppCompatActivity {
    private LinearLayout re01;
    private FragmentManager fm;
    private yt_MajorMajorFg ytLinkManFragment;
    private yt_ClassFg ytClassFg;
    private yt_MajorFg ytMajorFg;
    private yt_StuInfosFg ytStuInfosFg;
//    private yt_RoleFgDetil ytRoleFgDetil;
    private yt_StuInfosFgTest ytRoleFgDetil;
    private yt_RoleFg ytRoleFg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt__link_man);
        re01= (LinearLayout) super.findViewById(R.id.re01);
        fm=getSupportFragmentManager();
        initMoreFragment();
        initViewLisener();
    }
    private void initMoreFragment() {
        ytLinkManFragment=new yt_MajorMajorFg();
        fm.beginTransaction().add(R.id.container,ytLinkManFragment).commit();
    }
    public void replaceFragmet(int flag,int id,String name,String role){
       switch (flag){
           case 0:
               ytLinkManFragment=new yt_MajorMajorFg();//专业和职务
               Bundle bundle=new Bundle();
               bundle.putInt("id", id);
               bundle.putString("role",role);
               ytLinkManFragment.setArguments(bundle);//给Fragment传参
               fm.beginTransaction().replace(R.id.container, ytLinkManFragment).commit();
               break;
           case 1:
               ytClassFg=new yt_ClassFg(); //各种班级
               Bundle bundle2=new Bundle();
               bundle2.putInt("id", id);
               bundle2.putString("role", role);
               ytClassFg.setArguments(bundle2);
               fm.beginTransaction().replace(R.id.container,ytClassFg).commit();
               break;
           case 2:
               ytMajorFg=new yt_MajorFg(); //各种专业
               Bundle bundle3=new Bundle();
               bundle3.putString("role",role);
               bundle3.putString("name",name);
               ytMajorFg.setArguments(bundle3);
               fm.beginTransaction().replace(R.id.container,ytMajorFg).commit();
               break;
           case 3:
               ytStuInfosFg=new yt_StuInfosFg();  //各种学生
               Bundle bundle4=new Bundle();
               bundle4.putInt("id", id);
               bundle4.putString("role", role);
               bundle4.putString("name",name);
               ytStuInfosFg.setArguments(bundle4);
               fm.beginTransaction().replace(R.id.container,ytStuInfosFg).commit();
               break;
           case 4:
               ytRoleFgDetil=new yt_StuInfosFgTest();                       //学生老师班长
               Bundle bundle6=new Bundle();
               bundle6.putString("role",role);//教师
               bundle6.putString("name",name);//职务
               ytRoleFgDetil.setArguments(bundle6);
               fm.beginTransaction().replace(R.id.container,ytRoleFgDetil).commit();
           case 5:
               ytRoleFg=new yt_RoleFg();   //详细
               Bundle bundle5=new Bundle();
               bundle5.putString("role",role);//职务
               bundle5.putString("name",name);//职务
               ytRoleFg.setArguments(bundle5);
               fm.beginTransaction().replace(R.id.container,ytRoleFg).commit();
               break;
       }
    }
    private void initViewLisener(){
        re01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void replaceFragmetBars(int flag){
        switch (flag){
            case 0:
                ytLinkManFragment=new yt_MajorMajorFg();//专业和职务
                fm.beginTransaction().replace(R.id.container, ytLinkManFragment).commit();
                break;
            case 1:
                ytClassFg=new yt_ClassFg(); //各种班级
                fm.beginTransaction().replace(R.id.container,ytClassFg).commit();
                break;
            case 2:
                ytMajorFg=new yt_MajorFg(); //各种专业
                fm.beginTransaction().replace(R.id.container,ytMajorFg).commit();
                break;
            case 3:
                ytStuInfosFg=new yt_StuInfosFg();  //各种学生
                fm.beginTransaction().replace(R.id.container,ytStuInfosFg).commit();
                break;
            case 4:
                ytRoleFgDetil=new yt_StuInfosFgTest();                       //学生老师班长
                fm.beginTransaction().replace(R.id.container,ytRoleFgDetil).commit();
            case 5:
                ytRoleFg=new yt_RoleFg();   //详细
                fm.beginTransaction().replace(R.id.container,ytRoleFg).commit();
                break;
        }
    }
}
