package com.example.webviewbanner.dao;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.webviewbanner.R;

/**
 * Created by QinQinBaoBei on 2017/12/4.
 */

public class CustomFlowLayout extends ViewGroup{
    public CustomFlowLayout(Context context) {
        super(context);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //找布局添加
        View view = LayoutInflater.from(context).inflate(R.layout.custom_flow_layout,null);
        addView(view);

    }

    public CustomFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
          measureChildren(widthMeasureSpec,heightMeasureSpec);
    }


    //测量宽高
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //找到宽高
        int width = getWidth();
        int height = getHeight();
        int th=0;
        int tw=0;
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            //判断
            if(tw+child.getWidth()<width){

            }else{
                tw=0;
                th +=child.getMeasuredHeight();
            }

            child.layout(tw, th, tw + child.getMeasuredWidth(), th + child.getMeasuredHeight());
            tw += child.getMeasuredWidth();
        }
    }
}
