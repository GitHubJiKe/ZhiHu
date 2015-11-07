package com.ypf.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ypf.zhihu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter {
    List<String> data=new ArrayList<>();
    int imgs[];
    Context context;
    public MyRecycleViewAdapter(Context context,List<String>data,int imgs[]){
        this.context=context;
        this.data=data;
        this.imgs=imgs;
    }

    /**
     * 定义接口供外部回调，点击事件
     */
    public interface onItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private onItemClickListener mOnItemClickLitener;

    public void setOnItemClickLitener(onItemClickListener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.tv.setText(data.get(position).toString());
        myViewHolder.iv.setImageResource(imgs[position]);
        if (mOnItemClickLitener!=null){
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=myViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(myViewHolder.itemView,position);
                }
            });
        }
        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = myViewHolder.getLayoutPosition();
                mOnItemClickLitener.onItemLongClick(myViewHolder.itemView, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.textView3);
            iv= (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
