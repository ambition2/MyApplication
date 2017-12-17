package com.example.webviewbanner.model;

import com.example.webviewbanner.bean.AddCarBean;
import com.example.webviewbanner.okthhp.OnNetListener;

import java.util.Map;

/**
 * Created by lenovo on 2017/12/12.
 */

public interface IShowAddCarModel {
     void ShowAddCar(Map<String,String> parms, OnNetListener<AddCarBean> onNetListener);
}
