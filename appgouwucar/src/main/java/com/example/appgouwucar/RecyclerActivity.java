package com.example.appgouwucar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.appgouwucar.adaper.RecyclerAdaper;
import com.example.appgouwucar.bean.RecyclerBean;
import com.example.appgouwucar.precener.Recyclerpersenter;
import com.example.appgouwucar.view.IShowRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class RecyclerActivity extends Activity implements IShowRecyclerView {
  XRecyclerView xre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        xre=findViewById(R.id.xre);
        Recyclerpersenter  presenter=new Recyclerpersenter(RecyclerActivity.this,this);
        presenter.showdata(1+"",1+"");
        LinearLayoutManager manager=new LinearLayoutManager(this);
        xre.setLayoutManager(manager);

    }

    @Override
    public void showdata(final List<RecyclerBean.DataBean> bean) {
        RecyclerAdaper adaper=new RecyclerAdaper(RecyclerActivity.this,bean);
        xre.setAdapter(adaper);
         adaper.setOnItemClickListener(new RecyclerAdaper.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 Toast.makeText(RecyclerActivity.this, "调到详情页!"+position, Toast.LENGTH_LONG).show();
                 String pid = String.valueOf(bean.get(position).getPid());
                 Intent intent=new Intent(RecyclerActivity.this,XiangqingActivity.class);
                 intent.putExtra("pid",pid);
                 startActivity(intent);
             }
         });


    }
}
