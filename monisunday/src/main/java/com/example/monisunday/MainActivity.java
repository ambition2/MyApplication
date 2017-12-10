package com.example.monisunday;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.monisunday.adaper.Myadaper;
import com.example.monisunday.bena.RecycykerBean;
import com.example.monisunday.precneter.Showdatapersenter;
import com.example.monisunday.view.IShowdataView;
;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements IShowdataView {

    Showdatapersenter presenter;
   XRecyclerView xre;
    int shuxin=1;//定义的变量，用来刷新加载用
    EditText ed;
    Button bt,bu1;
    Handler handler=new Handler();
    List<RecycykerBean.DataBean> list=new ArrayList<>();
    String name="笔记本";
    int num=1;
    Myadaper adaper;
    boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
         presenter=new Showdatapersenter(this);
        String s = String.valueOf(num);
        presenter.showdata(name,s);
        setadaper();
        /**
         * 判断点击切换布局
         */

     bu1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if(flag==false){
                 flag=true;
                 xre.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                 adaper = new Myadaper(MainActivity.this, list,flag);
                 xre.setAdapter(adaper);
                 adaper.notifyDataSetChanged();
             }else{
                 flag =false;
                 xre.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                 adaper = new Myadaper(MainActivity.this, list,flag);
                 xre.setAdapter(adaper);
                 adaper.notifyDataSetChanged();

             }
         }
     });
        /**
         * 刷新 和加载
         */
        xre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num=1;
                        presenter.showdata(name,num+"");
                        setadaper();
                        xre.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num++;
                        presenter.showdata(name,num+"");
                        setadaper();
                        xre.loadMoreComplete();
                    }
                },2000);


            }
        });
        /**
         * 实现搜索功能
         */
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = ed.getText().toString().trim();
                //判断得到输入框的值
                //不为空的时候走第一个
                //为空的时候走第二个
                if(!TextUtils.isEmpty(s1))
                {
                    list.clear();
                    presenter.showdata(s1,1+"");
                    setadaper();
                }else {
                    list.clear();
                    presenter.showdata(name,1+"");
                    setadaper();
                }
            }
        });

    }
//设置适配器
    private void setadaper() {
        if(adaper==null) {
            xre.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
            adaper = new Myadaper(MainActivity.this, list,flag);
            xre.setAdapter(adaper);
        }else{
            adaper.notifyDataSetChanged();
        }

    }

    private void initview() {
        xre=findViewById(R.id.xre);
        ed=findViewById(R.id.ed);
        bt=findViewById(R.id.bt);
        bu1=findViewById(R.id.bu1);

    }


    @Override
    public void showdatare(final List<RecycykerBean.DataBean> been) {
     list.addAll(been);
        setadaper();
    }

}
