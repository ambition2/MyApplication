package com.example.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapp.fragment.fra_car;
import com.example.myapp.fragment.fra_my;
import com.example.myapp.fragment.fra_shouye;
import com.example.myapp.fragment.fra_weitao;
import com.example.myapp.fragment.fra_xiaoxi;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends FragmentActivity {
    public RadioGroup rg;

    public RadioButton rb1;
    public RadioButton rb2;
    public RadioButton rb3;
    public RadioButton rb4;
    public RadioButton rb5;
    public List<Fragment> list;
    public ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        initView();
        initData();
        getData();
       Myvpadaper myadaper=new Myvpadaper(getSupportFragmentManager());
        vp.setAdapter(myadaper);



    }

    private void getData() {

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new fra_shouye());
        list.add(new fra_weitao());
        list.add(new fra_xiaoxi());
        list.add(new fra_car());
        list.add(new fra_my());

    }

    private void initView() {
        vp =  findViewById(R.id.vp);
        rg = findViewById(R.id.shouye_rg1);
        rb1 =  findViewById(R.id.shouye_rb1);
        rb2 =  findViewById(R.id.shouye_rb2);
        rb3 =  findViewById(R.id.shouye_rb3);
        rb4 = findViewById(R.id.shouye_rb4);
        rb5 =  findViewById(R.id.shouye_rb5);

    }


    class Myvpadaper extends FragmentPagerAdapter{

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        public Myvpadaper(FragmentManager fm) {
            super(fm);
        }
    }

}
