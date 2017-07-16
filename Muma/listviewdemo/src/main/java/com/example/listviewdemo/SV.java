package com.example.listviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * Created by john on 5/5/17.
 */

public class SV extends ScrollView {
    private OnScrollListener listener;
    public SV(Context context) {
        super(context);
    }
    public SV(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //传进来一个接口对象
    public interface OnScrollListener{
        void loadMore();
    }

    //设置滑动监听
    public void setOnScrollListener(OnScrollListener listener){
        this.listener = listener;
    }


    //手指对屏幕的触摸事件---》监听是否滑到底
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        View childView = getChildAt(0);
        //获取测量高度(总高度)
        int measuredHeight = childView.getMeasuredHeight();
        //获取画出屏幕高度
        int scrollY = getScrollY();
        //获取可视区域
        int height = getHeight();
        //判断位置
        if(measuredHeight==scrollY+height){
            Toast.makeText(getContext(), "到底了！", 0).show();
            listener.loadMore();
        }
        return super.onTouchEvent(ev);
    }
}