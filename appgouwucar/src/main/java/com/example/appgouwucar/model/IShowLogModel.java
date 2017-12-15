package com.example.appgouwucar.model;


import com.example.appgouwucar.bean.LogBean;
import com.example.appgouwucar.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/11.
 */

public interface IShowLogModel {
    public  void showlogdata(String name, String pwd, final OnNetListener<LogBean> onNetListener);
}
