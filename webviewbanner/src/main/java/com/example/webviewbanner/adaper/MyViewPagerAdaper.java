package com.example.webviewbanner.adaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by lenovo on 2017/12/11.
 */

public class MyViewPagerAdaper extends FragmentPagerAdapter {

    private List<Fragment> list;
    public MyViewPagerAdaper(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
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
