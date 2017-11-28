package com.example.studyone;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.example.studyone.bean.OneDataBean;
import com.example.studyone.precenter.ShowPrecenter;
import com.example.studyone.view.IShowDataView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends Activity implements IShowDataView{
    ShowPrecenter precenter;
    int page=1;
    Myadaper  myadaper;
    XRecyclerView xrecycler;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xrecycler= findViewById(R.id.xrecycler);
        precenter=new ShowPrecenter(this);
        precenter.getdata(page);
        myadaper=new Myadaper(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        xrecycler.setLayoutManager(manager);
        xrecycler.setAdapter(myadaper);

    }

    @Override
    public void success(OneDataBean bean) {
        System.out.println(bean);
        myadaper.add(bean.getData());
        xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        precenter.getdata(page);
                        myadaper.notifyDataSetChanged();
                        xrecycler.refreshComplete();
                    }
                },1000);

            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        precenter.getdata(page);
                        myadaper.notifyDataSetChanged();
                        xrecycler.loadMoreComplete();
                    }
                },1000);

            }
        });

    }

    @Override
    public void error(Exception e) {

    }
}
