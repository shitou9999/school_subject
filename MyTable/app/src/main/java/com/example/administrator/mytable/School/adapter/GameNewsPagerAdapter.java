package com.example.administrator.mytable.School.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class GameNewsPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titles;
    public GameNewsPagerAdapter(FragmentManager fm) {//构造函数，注意传的参数
        super(fm);
        fragmentList=new ArrayList<Fragment>();
        titles=new ArrayList<String>();
    }
    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        titles.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {//从集合中返回一个Fragment
        return fragmentList.get(position);
    }


}
