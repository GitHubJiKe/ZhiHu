package com.ypf.zhihu.activity;

import android.app.Activity;
import android.os.Bundle;

import com.ypf.zhihu.R;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
//        Intent intent = getIntent();
//        Object news= intent.getExtras();
        //自定义Adapter，适配数据……

    }

}
