package com.example.frescostudy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SenActivity extends Activity {
  TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sen);
        tv=findViewById(R.id.tvv);
        //接收值 也就是注册
        EventBus.getDefault().register(this);
    }
    //使用EventBus必须要使用注解 而注解里面的参数，加上没坏处，如果
    //avivity和fragment传值得话，逗号后面的一定要加上让他运行在主线程
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    //定义的方法 因为传过来的值是string类型的所以在参数里面定义个string类型，s就是穿过来的值
    public void show (String s)
    {
        //给当前的值赋值
          tv.setText(s);
    }
//但是在销毁的时候一定要把EventBus销毁掉取消注册事件
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
