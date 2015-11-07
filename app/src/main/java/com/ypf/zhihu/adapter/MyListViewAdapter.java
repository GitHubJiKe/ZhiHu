package com.ypf.zhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ypf.zhihu.R;

import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
public class MyListViewAdapter extends BaseAdapter {
    List<String>data;
    Context context;
    LayoutInflater lif;
    public MyListViewAdapter(Context context,List<String> data){
        this.context=context;
        this.data=data;
        this.lif=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=lif.inflate(R.layout.item_menu,null);
            holder.tv= (TextView) convertView.findViewById(R.id.textView2);
            holder.iv= (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        holder= (ViewHolder) convertView.getTag();
        holder.tv.setText(data.get(position));
        holder.iv.setBackgroundResource(R.drawable.ic_menu_follow);
        View.OnClickListener l=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=true;
                if (v instanceof TextView){
                    //如果点击的是左侧的文本信息，响应事件处理
                    Toast.makeText(context,"进入"+data.get(position),Toast.LENGTH_SHORT).show();
                }else if (v instanceof ImageView){
                    //如果点击的是右侧的图标，改变背景图
                    if(flag){
                        ((ImageView) v).setImageResource(R.drawable.ic_menu_arrow);
                        flag=false;
                    }else {
                        ((ImageView) v).setImageResource(R.drawable.ic_menu_follow);
                    }

                }
            }
        };
        holder.tv.setOnClickListener(l);
        holder.iv.setOnClickListener(l);
        return convertView;
    }

    class ViewHolder{
        TextView tv;
        ImageView iv;
    }
}
