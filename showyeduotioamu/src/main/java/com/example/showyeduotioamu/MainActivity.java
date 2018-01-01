package com.example.showyeduotioamu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.showyeduotioamu.fragment.FenleiFragment;
import com.example.showyeduotioamu.fragment.ShowyeFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {
    BottomTabBar bottomTabbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabbar= (BottomTabBar) findViewById(R.id.bottom_tabbar);
        bottomTabbar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(18)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("首页",R.drawable.home_, ShowyeFragment.class)
                .addTabItem("分类",R.drawable.classify, FenleiFragment.class)
                .addTabItem("购物车",R.drawable.cart, FenleiFragment.class)
                .addTabItem("个人",R.drawable.mine, FenleiFragment.class)
                .isShowDivider(true)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }
}
