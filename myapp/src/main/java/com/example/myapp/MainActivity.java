package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {


    private static final String APP_FIRST_INTO = null;
    ImageView welcome;//全局的控件
    //定义handler
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    //初始化一个时间
    int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = (ImageView) findViewById(R.id.welcome);
        //得到SharedPreferences的对象
        SharedPreferences sp= this.getSharedPreferences("user", this.MODE_PRIVATE);
        //使用得到是SharedPreferences的对象来获取edit
        SharedPreferences.Editor edit = sp.edit();

        //用sp得到一个布尔 随便给个名字，先给他一个false
        boolean aBoolean = sp.getBoolean("first",false);
        //false表示第二次 直接跳

        if (aBoolean){
            Intent intent=new Intent(MainActivity.this,NextActivity.class);
            startActivity(intent);
            finish();
        }else {
            //为true代表第一次 使用handler发送，把boolean设置为true 并提交
            edit.putBoolean("first",true).commit();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //时间的判断
                    time--;
                    if(time==0)
                    {
                        Intent intent=new Intent(MainActivity.this,NextActivity.class);
                        startActivity(intent);

                    }
                    handler.postDelayed(this,1000);
                }
            },1000);
        }
        //如果是直接跳的话每次都出现欢迎页我们只需写handler代码就可以了

        // handler.postDelayed(new Runnable() {
        //  @Override
        //  public void run() {
        //时间的判断
        //    time--;
        //  if(time==0)
        //  {
        //   Intent intent=new Intent(WelcomActivity.this,LoginActivity.class);
        //   startActivity(intent);

        // }
        //  handler.postDelayed(this,1000);
        //  }
        //    },1000);

    }
    //最后在销毁
    protected void onDestroy() {

        handler.removeCallbacksAndMessages(null);

        super.onDestroy();
    }
}
