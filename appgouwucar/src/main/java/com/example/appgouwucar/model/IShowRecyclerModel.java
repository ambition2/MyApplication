package com.example.appgouwucar.model;

import com.example.appgouwucar.bean.RecyclerBean;
import com.example.appgouwucar.okthhp.OnNetListener;


/**
 * Created by lenovo on 2017/12/11.
 */

public interface IShowRecyclerModel {
    public void ShowRecyclerData(String pscid,String page, final OnNetListener<RecyclerBean> onNetListener);
}
