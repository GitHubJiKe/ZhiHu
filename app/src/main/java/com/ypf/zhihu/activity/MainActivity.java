package com.ypf.zhihu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.ypf.zhihu.R;
import com.ypf.zhihu.adapter.ListPopupWindowAdapter;
import com.ypf.zhihu.adapter.MyRecycleViewAdapter;
import com.ypf.zhihu.adapter.MyViewPagerAdapter;
import com.ypf.zhihu.view.MySlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ViewPager vp;
    MySlidingMenu slidingMenu;
    TextView tv1;
    TextView tv2;
    RecyclerView rlv;
    private ArrayList<String> mArrayList=new ArrayList<>();
    private ListPopupWindow mListPopupWindow;
    private ListPopupWindowAdapter mListPopupWindowAdapter;
    List<String> data=new ArrayList<>();
    //图片数组
    private int imgs[]={
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化静态数据
        initData();
        setContentView(R.layout.content_main);
        //初始化View
        setViews();
        //初始化PopMenu
        setPopMenu();
        //设置RecycleView各个参数
        setRecycleView();
        //创建MyRecycleViewAdapter对象
        final MyRecycleViewAdapter rlvAdapter = getMyRecycleViewAdapter();
        //设置Adapter
        rlv.setAdapter(rlvAdapter);
        //设置Viewpager和Adapter
        setViewPager();
        //新起一个线程不停地轮播ViewPager中的图片
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                int length=data.size();
                int item=0;
                if (item!=length){
                    vp.setCurrentItem(item);
                }else{
                    item=0;
                }
                item++;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    private void setPopMenu() {
        mListPopupWindow=new ListPopupWindow(this);
        //自定义Adapter
        mListPopupWindowAdapter=new ListPopupWindowAdapter(mArrayList, this);
        mListPopupWindow.setAdapter(mListPopupWindowAdapter);
        mListPopupWindow.setWidth(200);
        mListPopupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
                Toast.makeText(MainActivity.this, "点击了"+mArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //指定anchor
                mListPopupWindow.setAnchorView(v);
                mListPopupWindow.show();
            }
        });
    }

    private void setViewPager() {
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(this,imgs);
        vp.setAdapter(adapter);
    }

    @NonNull
    private MyRecycleViewAdapter getMyRecycleViewAdapter() {
        final MyRecycleViewAdapter rlvAdapter=new MyRecycleViewAdapter(this,data,imgs);
        rlvAdapter.setOnItemClickLitener(new MyRecycleViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this,ContentActivity.class);
                //intent.putExtra("content","新闻内容");
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                rlvAdapter.notifyItemRemoved(position);
            }
        });
        return rlvAdapter;
    }

    private void setRecycleView() {
        rlv= (RecyclerView) findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        rlv.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        rlv.setHasFixedSize(true);
        rlv.addItemDecoration(new RecyclerView.ItemDecoration() {
            //使用系统的分割线
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                final int[] ATTRS = new int[]{
                        android.R.attr.listDivider
                };
                Drawable mDivider = MainActivity.this.obtainStyledAttributes(ATTRS).getDrawable(0);
                final int left = parent.getPaddingLeft();
                final int right = parent.getWidth() - parent.getPaddingRight();
                /**
                 * 根据item个数画分割线
                 */
                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    RecyclerView v = new RecyclerView(parent.getContext());
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                            .getLayoutParams();
                    final int top = child.getBottom() + params.bottomMargin;
                    final int bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }
        });
    }

    private void setViews() {
        vp= (ViewPager) findViewById(R.id.vp1);
        slidingMenu=new MySlidingMenu(this);
        tv1= (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示侧滑菜单
                slidingMenu.showMenu();
            }
        });
        tv2= (TextView) findViewById(R.id.tv2);
        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击ViewPager的相应事件
                Toast.makeText(MainActivity.this, "知乎", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        //联网获得数据
//        String url="http://www.baidu.com";
//        try {
//            HttpGetData.getData(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        data.add("news one");
        data.add("news two");
        data.add("news three");
        mArrayList.add("夜间模式");
        mArrayList.add("设置选项");
    }
}
