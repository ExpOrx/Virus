package com.example.listviewdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by john on 5/5/17.
 */

public class LoadImageFromAssets {
    public static void load(final Activity activity, final String name, final ImageView iv){
        new Thread(){
            public void run(){
                try {
                    //从assets/imgs里获取图片资源，传入图片名字
                    InputStream stream = activity.getAssets().open("imgs/"+name);
                    final Bitmap bitmap = BitmapFactory.decodeStream(stream);
                    //主线程更新UI
                    activity.runOnUiThread(new Runnable(){
                        public void run(){
                            //获取图片宽高
                            int bitH = bitmap.getHeight();
                            int bitW = bitmap.getWidth();
                            //获取LayoutParams设置宽高，使图片填充整个ImageView
                            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv.getLayoutParams();
                            /**
                             * 图片的宽高比等于ImageView的宽高比，因为ImageView宽度已经确定(1/3父窗体)，通过计算
                             * 得到ImageView的高度
                             */
                            lp.height = bitH*lp.width/bitW;
                            //将参数设置到ImageView
                            iv.setLayoutParams(lp);
                            iv.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}