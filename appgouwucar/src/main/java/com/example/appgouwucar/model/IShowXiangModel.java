package com.example.appgouwucar.model;

import com.example.appgouwucar.bean.XiangqingBean;
import com.example.appgouwucar.okthhp.OnNetListener;

import java.util.Map;

/**
 * Created by lenovo on 2017/12/13.
 */

public interface IShowXiangModel {
    public void showxiangdata(Map<String,String> panas, final OnNetListener<XiangqingBean> onNetListener);
}
