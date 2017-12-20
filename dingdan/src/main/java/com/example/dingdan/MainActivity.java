package com.example.dingdan;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dingdan.fragment.Frag_All;
import com.example.dingdan.fragment.Frag_Wait;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
  List<Fragment> list;
    RadioGroup rg;
    RadioButton all,dzf,yzf,yqx;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
        list=new ArrayList<>();
        list.add(new Frag_All());
        list.add(new Frag_Wait());
        MyViewpager pager=new MyViewpager(getSupportFragmentManager());
        vp.setAdapter(pager);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i)
                {
                    case R.id.all:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.dzf:
                        vp.setCurrentItem(1);
                        break;

                }

            }
        });

    }

    private void initdata() {
        rg=findViewById(R.id.rg);
        all=findViewById(R.id.all);
        dzf=findViewById(R.id.dzf);
        yzf=findViewById(R.id.yzf);
        yqx=findViewById(R.id.yqx);
        vp=findViewById(R.id.vp);
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
