package com.ypf.zhihu.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tarena.utils.ImageCircleView;
import com.ypf.zhihu.R;
import com.ypf.zhihu.adapter.MyListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
public class MySlidingMenu {
    SlidingMenu slidingMenu;
    TextView tv1;
    Activity activity;
    ImageCircleView icv;
    LinearLayout ll3;
    ListView lv;
    List<String>data=new ArrayList<>();
    public MySlidingMenu(final Activity activity){
        this.activity=activity;
        //初始化侧滑菜单
        setSlidmingMenu(activity);
        LayoutInflater inflate=LayoutInflater.from(activity);
        //初始化各个View
        setViews(activity);
        //初始化静态数据
        initData();
        //创建Adapter对象
        MyListViewAdapter adapter=new MyListViewAdapter(activity,data);
        //设置Adapter
        lv.setAdapter(adapter);
    }

    private void setSlidmingMenu(Activity activity) {
        slidingMenu=new SlidingMenu(activity);
        slidingMenu.setMenu(R.layout.slidingmenu_layout);
        slidingMenu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        int windowWidth=activity.getResources().getDisplayMetrics().widthPixels;
        slidingMenu.setBehindOffset(windowWidth / 2);
    }

    private void initData() {
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
        data.add("电影日报");
    }

    private void setViews(final Activity activity) {
        ll3= (LinearLayout) activity.findViewById(R.id.ll3);
        icv= (ImageCircleView) activity.findViewById(R.id.circleview);
        tv1= (TextView) activity.findViewById(R.id.tv1);
        lv= (ListView) activity.findViewById(R.id.lv1);
        icv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击头像的事件相应
                Toast.makeText(activity, "我的头像", Toast.LENGTH_SHORT).show();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=true;
                if (flag){
                    //点击收藏，改变背景图
                    tv1.setBackgroundResource(R.drawable.ic_collected_white);
                    flag=false;
                }else {
                    //点击收藏，改变背景图
                    tv1.setBackgroundResource(R.drawable.ic_collect_white);
                }

            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //侧滑菜单的隐藏
                slidingMenu.toggle(true);
            }
        });
    }

    /**
     * 提供show方法，供外部调用显示菜单
     */
    public void showMenu()
    {
        slidingMenu.showMenu();
    }
}
