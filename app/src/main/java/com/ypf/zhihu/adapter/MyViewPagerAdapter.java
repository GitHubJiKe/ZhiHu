package com.ypf.zhihu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    Context context;
    int imgs[];
    public MyViewPagerAdapter(Context context,int imgs[]){
        this.context=context;
        this.imgs=imgs;
    }
    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        //1.item view
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//图片拉伸
        //2.item data
        int img=imgs[position];
        //3.将itemdata当到item view上构建item
        imageView.setImageResource(img);
        //4.将item添加到viewGroup中
        container.addView(imageView);
        return imageView;//item
    }
}
