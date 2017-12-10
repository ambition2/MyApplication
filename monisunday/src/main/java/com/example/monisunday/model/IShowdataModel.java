package com.example.monisunday.model;

import com.example.monisunday.bena.RecycykerBean;
import com.example.monisunday.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/9.
 */

public interface IShowdataModel {
     void showdata(String keywords, String page, final OnNetListener<RecycykerBean> onNetListener);
}
