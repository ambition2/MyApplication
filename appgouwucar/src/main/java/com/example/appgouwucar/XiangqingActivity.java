package com.example.appgouwucar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appgouwucar.fragment.PingFrangment;
import com.example.appgouwucar.fragment.ShopFrangment;
import com.example.appgouwucar.fragment.XiangFrangment;
import com.example.appgouwucar.precener.AddCarPrecenter;
import com.example.appgouwucar.view.IShowAddcarView;

import java.util.ArrayList;
import java.util.List;

public class XiangqingActivity extends FragmentActivity implements IShowAddcarView{
    RadioButton shop;
    RadioButton xiangqing;
    RadioButton pingjia;
    LinearLayout selectcar;
    ViewPager vp;
    RadioGroup rg;
    String pid;
    Button car;
    List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
     //得到上个接口中的pid做当前接口的商品详情页面
        final Intent intent = getIntent();

        pid = intent.getStringExtra("pid");
        Bundle bundle=new Bundle();
        bundle.putString("pid",pid);
        initdata();
         list=new ArrayList<>();
        ShopFrangment shopFragment=new ShopFrangment();
        shopFragment.setArguments(bundle);
        list.add(shopFragment);
        list.add(new XiangFrangment());
        list.add(new PingFrangment());
        MyViewpager pager=new MyViewpager(getSupportFragmentManager());
        vp.setAdapter(pager);
        final AddCarPrecenter precenter=new AddCarPrecenter(this);
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
                    case  R.id.pingjia:
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
        selectcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent1=new Intent(XiangqingActivity.this,SelectCarActivity.class);
                startActivity(intent1);
            }
        });


    }

    private void initdata() {
        shop=findViewById(R.id.shop);
        xiangqing=findViewById(R.id.xiangqing);
        pingjia=findViewById(R.id.pingjia);
        vp=findViewById(R.id.vp);
        rg=findViewById(R.id.rg);
        car= findViewById(R.id.car);
        selectcar=findViewById(R.id.selectcar);
    }


    @Override
    public void showcar(String str) {
        Toast.makeText(XiangqingActivity.this, "我点了购物车!"+str.toString(), Toast.LENGTH_LONG).show();
    }

    class MyViewpager extends FragmentPagerAdapter{

        public MyViewpager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
