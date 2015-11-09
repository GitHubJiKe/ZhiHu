package com.ypf.zhihu.http;


import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.ypf.zhihu.entity.News;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/11/9.
 */
public class HttpGetData {
    public static String getData(String url) throws IOException{
        URL url1=new URL(url);
        HttpURLConnection coon= (HttpURLConnection) url1.openConnection();
        coon.setRequestMethod("GET");//设置请求方式GET
        coon.setRequestProperty("User-Agent", "Mozilla/5.0");//优先级别
        try {
            //如果联网成功
            if (coon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //将获取到的输入流封装到BufferedReader in
                BufferedReader in = new BufferedReader(new InputStreamReader(coon.getInputStream()));
                String inputLine;
                //线程安全的StringBuilder
                final StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);//整行读取
                }
                //读取完毕关闭，防止内存泄露
                in.close();
                return response.toString();
            } else {
                //联网失败，抛出异常
                throw new IOException("Network Error - response code: " + coon.getResponseCode());
            }
        } finally {
            //无论成功失败，都要关闭链接
            coon.disconnect();
        }
    }

    /**
     * 采用第三方框架请求网络获取数据
     * @param url
     */
    public  static void getDataAsync(String url){
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode,Header[] headers, String responseString) {
                News news=JSON.parseObject(responseString, News.class);//fast-json解析json字符串
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onFailure(int statusCode,Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


}
