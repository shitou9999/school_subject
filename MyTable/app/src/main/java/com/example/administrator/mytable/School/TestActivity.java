package com.example.administrator.mytable.School;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_MajorMajorFg;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Fragment.yt_StuInfosFgTest;

/**
 * Created by Administrator on 2016/1/26.
 */
public class TestActivity extends AppCompatActivity {
    private FragmentManager fm;
    private yt_MajorMajorFg ytLinkManFragment;
    //    private yt_RoleFgDetil ytRoleFgDetil;
    private yt_StuInfosFgTest ytRoleFgDetil;
    private LinearLayout re01;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt__link_man);
        re01= (LinearLayout) super.findViewById(R.id.re01);
        re01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.this.finish();
            }
        });
        fm=getSupportFragmentManager();
        initTest();
    }

    private void initTest() {
        ytRoleFgDetil=new yt_StuInfosFgTest();
        fm.beginTransaction().replace(R.id.container,ytRoleFgDetil).commit();
    }
}
