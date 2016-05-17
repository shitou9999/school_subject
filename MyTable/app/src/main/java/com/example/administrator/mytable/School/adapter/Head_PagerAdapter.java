package com.example.administrator.mytable.School.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 头部图片的适配器
 * Created by Administrator on 2015/12/26.
 */
public class Head_PagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageView> imageViewList;
    public Head_PagerAdapter() {
    }
    public Head_PagerAdapter(Context context, List<ImageView> imageViewList) {
        this.context = context;
        this.imageViewList = imageViewList;
    }
    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ImageView img=imageViewList.get(position);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


}
