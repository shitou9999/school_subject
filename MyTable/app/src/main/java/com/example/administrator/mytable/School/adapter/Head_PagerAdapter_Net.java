package com.example.administrator.mytable.School.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.HttpURL.HttpURL;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.WebView_News_Detil_Activity2;

import java.util.List;

/**
 * 头部图片的适配器
 * Created by Administrator on 2015/12/26.
 */
public class Head_PagerAdapter_Net extends PagerAdapter {
    private Context context;
    private List<Theme> imageViewList;
    private MyApplication app;
    public Head_PagerAdapter_Net() {
    }
    public Head_PagerAdapter_Net(Context context, List<Theme> imageViewList) {
        this.context = context;
        this.imageViewList = imageViewList;
        app=MyApplication.getInstance();
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
    public Object instantiateItem(final ViewGroup container, final int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        app.getImageLoader().get(HttpURL.HOST + "SchoolLife" + imageViewList.get(position).getImg(),
                ImageLoader.getImageListener(imageView, R.mipmap.banner_02, R.mipmap.banner_02));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebView_News_Detil_Activity2.class);
                Theme imgRes_net = imageViewList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",imgRes_net);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


}
