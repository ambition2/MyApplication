package com.example.appgouwucar.model;

import com.example.appgouwucar.bean.AddcarBean;
import com.example.appgouwucar.okthhp.OnNetListener;

import java.util.Map;

/**
 * Created by lenovo on 2017/12/13.
 */

public interface IShowAddCarModel {
    public  void showaddcar(Map<String,String> pams, OnNetListener<AddcarBean> onNetListener);
}
