package com.example.stuuuiodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.stuuuiodo.adaper.RecyclerAdaper;
import com.example.stuuuiodo.bean.RecyclerBean;
import com.example.stuuuiodo.precneter.RecyclerPrecenter;
import com.example.stuuuiodo.view.IRecyclerView;

public class MainActivity extends Activity implements IRecyclerView{
    RecyclerPrecenter precenter;
    RecyclerAdaper adaper;
    private int page=3;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        precenter=new RecyclerPrecenter(this);
        precenter.getdata(page);
        adaper =new RecyclerAdaper(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
       recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adaper);


    }

    @Override
    public void success(RecyclerBean bean) {
//         System.out.println("bean"+bean);
        adaper.adddata(bean.getData());
        adaper.setOnItemClickListener(new RecyclerAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "你好!", Toast.LENGTH_LONG).show();


                Intent intent=new Intent(MainActivity.this,OtherActivity.class);
                intent.putExtra("position",position+"");
                startActivity(intent);
            }
        });

    }

    @Override
    public void error(Exception e) {

    }
}
