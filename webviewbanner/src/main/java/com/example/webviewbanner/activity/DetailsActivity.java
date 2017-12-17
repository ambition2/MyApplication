package com.example.webviewbanner.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.webviewbanner.R;
import com.example.webviewbanner.adaper.MyViewPagerAdaper;
import com.example.webviewbanner.fragment.PingjiaFragment;
import com.example.webviewbanner.fragment.ShopFragment;
import com.example.webviewbanner.fragment.XiangqingFragment;
import com.example.webviewbanner.precenter.AddCarPrecenter;
import com.example.webviewbanner.view.IShowAddCarView;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends FragmentActivity implements IShowAddCarView{
  RadioButton shop;
    RadioButton xiangqing;
    RadioButton pingjia;
    ViewPager vp;
    RadioGroup rg;
    Button car;
    AddCarPrecenter precenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        final String pid = intent.getStringExtra("pid");


        shop=findViewById(R.id.shop);
        xiangqing=findViewById(R.id.xiangqing);
        pingjia=findViewById(R.id.pingjia);
          vp=findViewById(R.id.vp);
        rg=findViewById(R.id.rg);
       car= findViewById(R.id.car);
        List<Fragment> list=new ArrayList<>();
        ShopFragment shopFragment=new ShopFragment();
        list.add(shopFragment);
        list.add(new XiangqingFragment());
        list.add(new PingjiaFragment());
        MyViewPagerAdaper adaper=new MyViewPagerAdaper(getSupportFragmentManager(),list);
        vp.setAdapter(adaper);
         precenter=new AddCarPrecenter(this);

        Bundle bundle=new Bundle();
        bundle.putString("pid",pid);
        shopFragment.setArguments(bundle);



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                  switch (i)
                  {
                      case R.id.shop:
                          vp.setCurrentItem(0);
                          break;
                      case R.id.xiangqing:
                          vp.setCurrentItem(1);
                          break;
                      case R.id.pingjia:
                          vp.setCurrentItem(2);
                          break;
                  }

            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                String uid = user.getString("uid", "");
                precenter.showaddcar(uid,pid);
            }
        });

    }


    @Override
    public void showdata(String str) {
        Toast.makeText(DetailsActivity.this, "好像是成功了!", Toast.LENGTH_LONG).show();
    }
}
