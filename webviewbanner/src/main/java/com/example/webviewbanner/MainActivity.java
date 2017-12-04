package com.example.webviewbanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.webviewbanner.adaper.MylistViewAdaper;
import com.example.webviewbanner.adaper.ProductCatagoryAdapter;
import com.example.webviewbanner.bean.BannerBean;
import com.example.webviewbanner.bean.Catagorybean;
import com.example.webviewbanner.bean.ProductCatagorybean;
import com.example.webviewbanner.precenter.ShowdataPrecenter;
import com.example.webviewbanner.utils.MyBannerapp;
import com.example.webviewbanner.view.IShowdataview;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements IShowdataview{
    ShowdataPrecenter precenter;
    ListView lv;
    public Banner banner;
    public ExpandableListView mElv;
    public ScrollView mSv;
    List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         lv=findViewById(R.id.lv);
        banner =  findViewById(R.id.banner);
        mElv =  findViewById(R.id.elv);
        mSv = findViewById(R.id.sv);
         precenter=new ShowdataPrecenter(this);
        precenter.getCatagory();
        precenter.getbean();








    }


    @Override
    public void showCatagory(final List<Catagorybean.DataBean> list) {
        MylistViewAdaper adaper=new MylistViewAdaper(this,list);
        lv.setAdapter(adaper);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "你好!", Toast.LENGTH_LONG).show();
                int cid = list.get(i).getCid();
                precenter.getproduct(cid+"");

            }
        });



    }

    @Override
    public void showProductCatagory(List<ProductCatagorybean.DataBean> group, List<List<ProductCatagorybean.DataBean.ListBean>> child) {
       // Log.i("=================", "showProductCatagory: "+child.toString());
        ProductCatagoryAdapter adapter=new ProductCatagoryAdapter(this,group,child);
        mElv.setAdapter(adapter);
        //展开列表
        for (int i = 0; i < group.size(); i++) {
            mElv.expandGroup(i);
        }
        adapter.setLinster(new ProductCatagoryAdapter.updatalinster() {
            @Override
            public void setdata(View view, int position) {
                Toast.makeText(MainActivity.this, "我不好!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void showbanner(final List<BannerBean.DataBean> data) {
        list=new ArrayList<>();
        for (int i=0;i<data.size();i++){
              list.add(data.get(i).getIcon());
        }

        banner.isAutoPlay(true);
        //将图片集合放入，加载图片
        banner.setImages(list);

        //每隔3秒切换一次
        banner.setDelayTime(3000);
        // 加载
        banner.setImageLoader(new MyBannerapp());

        //样式小圆点
        banner.setBannerStyle(Banner.ACCESSIBILITY_LIVE_REGION_POLITE);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                  Intent intent=new Intent(MainActivity.this,WebviewActivity.class);
                  intent.putExtra("url",data.get(position).getUrl());
                   startActivity(intent);
            }
        });

        banner.start();
    }




    }



