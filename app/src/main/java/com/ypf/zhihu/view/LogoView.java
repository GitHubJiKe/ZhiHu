package com.ypf.zhihu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ypf.zhihu.R;

/**
 * Created by Administrator on 2015/11/5.
 */
public class LogoView extends View {
    //当前透明度
    private int alpha=0;

    /**
     * 构造方法
     * @param context
     */
    public LogoView(Context context) {
        super(context);
        //加载应用Logo图片
        logo= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.splash);
    }

    /**
     * 构造方法
     * @param context
     * @param attrs
     */
    public LogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public LogoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private Bitmap logo;
    //将透明度传递进来
    public void rePaint(int alpha){
        this.alpha = alpha;
        //抛出重回事件
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //清屏
        canvas.drawColor(Color.WHITE);
        //画笔准备
        Paint paint=new Paint();
        //设置画笔透明度
        paint.setAlpha(alpha);
        //绘制logo
        canvas.drawBitmap(logo, 0.0f, 0.0f, paint);
        super.onDraw(canvas);
    }
}
