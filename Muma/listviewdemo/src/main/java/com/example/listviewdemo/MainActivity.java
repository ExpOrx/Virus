package com.example.listviewdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {

    private LinearLayout ll1, ll2, ll3;
    // 每一个ImageView的宽度，是屏幕宽度的1/3
    private int imgvWidth;
    // 每次加载多少个
    private int pageSize = 15;
    //加载次数，每加载一次，pageIndex++
    private int pageIndex = 0;

    private String[] imgName;
    //布局有三个，添加到数组里，以便更新UI的时候使用。
    private LinearLayout lls[] = new LinearLayout[3];

    // 自定义ScrollView SV
    private SV sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        sv = (SV) findViewById(R.id.sv);
        // 用来盛放屏幕信息
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        //设置每个LinearLayout的宽度为整个屏幕的1/3
        imgvWidth = outMetrics.widthPixels / 3;
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        lls[0] = ll1;
        lls[1] = ll2;
        lls[2] = ll3;

        try {
            //获取所有文件名
            imgName = getAssets().list("imgs");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 第一次加载图片
        loadImage();
        //设置scrollView的滚动监听
        sv.setOnScrollListener(new SV.OnScrollListener() {
            @Override
            public void loadMore() {
                if (pageIndex * pageSize >= imgName.length) {
                    Toast.makeText(getApplicationContext(), "数据加载完成", 0).show();
                } else {
                    loadImage();
                }
            }
        });
    }

    // 进行图片加载
    private void loadImage() {
        //如果加载的数量比所有文件个数少，每次加载15张
        for (int i = pageIndex * pageSize; i < pageIndex * pageSize + pageSize
                && i < imgName.length; i++) {
            // 加载图片，实例化一个ImageView
            ImageView imgv = new ImageView(MainActivity.this);
            // 宽度是屏幕的1/3
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imgvWidth,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            imgv.setLayoutParams(lp);
            // 加载图片资源
            LoadImageFromAssets.load(MainActivity.this, imgName[i], imgv);
            //每次往不同的LinearLayout放一个带有图片的ImageView
            lls[i % 3].addView(imgv);
        }
        pageIndex++;
    }
}

