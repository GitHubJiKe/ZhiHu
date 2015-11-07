package com.ypf.zhihu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.ypf.zhihu.view.LogoView;

import java.util.Objects;

public class WelcomeActivity extends Activity {
    LogoView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logo=new LogoView(this);
        setContentView(logo);
        //创建异步任务
        LogoTask task=new LogoTask();
        //执行异步任务
        task.execute();
    }

    /**
     * 用来改变logo的透明度
     */
    private class LogoTask extends AsyncTask<Objects,Integer,String> {
        //current alpha
        int alpha=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 异步任务执行成功后跳转到系统主界面
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            super.onPostExecute(s);
        }
        /**
         * 修改Logo自定义视图图片透明度，并重绘，运行在主线程
         * */
        @Override
        protected void onProgressUpdate(Integer... values) {
            int temp=values[0].intValue();
            logo.rePaint(temp);
            super.onProgressUpdate(values);
        }
        /**
         * 异步任务，循环改变透明度取值
         * */
        @Override
        protected String doInBackground(Objects... params) {
            while (alpha < 255) {

                try {
                    Thread.sleep(100);
                    //此方法每次调用都会传递会主线程，当后台方法还在运行着的时候，每次调用此方法都会触发主线程
                    publishProgress(new Integer(alpha));
                    alpha += 5;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
}
