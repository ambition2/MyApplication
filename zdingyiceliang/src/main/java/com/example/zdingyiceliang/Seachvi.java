package com.example.zdingyiceliang;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 2017/12/4.
 *
 */

public class Seachvi extends ViewGroup {

    public Seachvi(Context context) {
        this(context,null);
    }

    public Seachvi(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected   void onLayout(boolean b, int i, int i1, int i2, int i3) {
         ImageView iv= (ImageView) getChildAt(0);
        LinearLayout ll= (LinearLayout) getChildAt(1);
        TextView tv= (TextView) getChildAt(2);
        //得到外层布局的高宽
        int height = getHeight();
        int width = getWidth();
         //得到当前控件的高宽
        int ivWidth = iv.getMeasuredWidth();
        int ivheight = iv.getMeasuredHeight();
       // height/2-ivheight/2  包含这个控件的高度的一半减去当前空间高度的一半算出顶部的点
        //10 右边的点 ivWidth+10 左边的点就是当前的宽度+右边的点
        // ivheight 当前的高是底部的点  顺序是左上右下
        //30这俩个数字可以控制当前控件的距离左侧的距离 类似于marginLeft
      iv.layout(30,height/2-ivheight/2,ivWidth+30,ivheight);

    //左侧的位置
        int tvHeight = tv.getMeasuredHeight();
        int tvWidth = tv.getMeasuredWidth();
        tv.layout(width-tvWidth-50,height/2-tvHeight/2,width-tvWidth+tvWidth,height/2-tvHeight/2+tvHeight);
           //中间的位置
        int llWidth = ll.getMeasuredWidth();
        int llHeight = ll.getMeasuredHeight();
        ll.layout(ivWidth+30+20,height/2-llHeight/2,width-tvWidth-50,height/2-llHeight/2+llHeight);
    }
}
