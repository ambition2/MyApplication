package com.example.appgouwucar.model;

import com.example.appgouwucar.bean.SelectCarBean;
import com.example.appgouwucar.okthhp.OnNetListener;

import java.util.Map;

/**
 * Created by lenovo on 2017/12/13.
 */

public interface IShowSelectCarModel {
    public  void showselectcar(Map<String,String> pas, OnNetListener<SelectCarBean> onNetListener);
}
