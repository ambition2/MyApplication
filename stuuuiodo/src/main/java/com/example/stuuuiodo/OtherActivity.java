package com.example.stuuuiodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stuuuiodo.adaper.Otheradaper;
import com.example.stuuuiodo.adaper.RecyclerAdaper;
import com.example.stuuuiodo.bean.RecyclerBean;
import com.example.stuuuiodo.precneter.RecyclerPrecenter;
import com.example.stuuuiodo.view.IRecyclerView;

public class OtherActivity extends Activity implements IRecyclerView {
    TextView tv;
    Otheradaper otheradaper;
    RecyclerView other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_layout);
        other = findViewById(R.id.other);
        tv = (TextView) findViewById(R.id.tvv1);
        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
//        tv.setText(position+"");
        RecyclerPrecenter precenter = new RecyclerPrecenter(this);
        precenter.getdata(Integer.parseInt(position));
        otheradaper = new Otheradaper(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        other.setLayoutManager(manager);
        other.setAdapter(otheradaper);


    }

    @Override
    public void success(RecyclerBean bean) {
        otheradaper.adddata(bean.getData());
      otheradaper.setOnItemClickListenertwo(new Otheradaper.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
              Toast.makeText(OtherActivity.this, "我不是特别好!", Toast.LENGTH_LONG).show();
          }
      });


    }

    @Override
    public void error(Exception e) {

    }
}

