package com.example.chenyunfei20171129;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.example.chenyunfei20171129.adaper.MyreviewAdaper;
import com.example.chenyunfei20171129.bean.TwoDataBean;
import com.example.chenyunfei20171129.precenter.Showdatapercenter;
import com.example.chenyunfei20171129.view.IShowdataview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends Activity implements IShowdataview{
    XRecyclerView xre;
    Showdatapercenter precnter;
    int path=1;
    MyreviewAdaper adaper;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xre=findViewById(R.id.rec);
         precnter=new Showdatapercenter(this);
        precnter.getdtaa(path);
        adaper=new MyreviewAdaper(MainActivity.this);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        xre.setLayoutManager(manager);
        xre.setAdapter(adaper);
        xre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        path++;
                        precnter.getdtaa(path);
                        adaper.notifyDataSetChanged();
                        xre.refreshComplete();
                    }
                },1000);

            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        path++;
                        precnter.getdtaa(path);
                        adaper.notifyDataSetChanged();
                        xre.loadMoreComplete();
                    }
                },1000);

            }
        });

    }

    @Override
    public void success(TwoDataBean bean) {
           System.out.print(bean);
        adaper.add(bean);
    }

    @Override
    public void error(Exception e) {

    }
}
