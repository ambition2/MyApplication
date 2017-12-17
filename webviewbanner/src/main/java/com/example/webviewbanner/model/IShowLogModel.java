package com.example.webviewbanner.model;


import com.example.webviewbanner.bean.LogBean;
import com.example.webviewbanner.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/11.
 */

public interface IShowLogModel {
    public  void showlogdata(String name, String pwd, final OnNetListener<LogBean> onNetListener);
}
