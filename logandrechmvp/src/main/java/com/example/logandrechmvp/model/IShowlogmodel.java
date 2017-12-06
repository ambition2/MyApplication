package com.example.logandrechmvp.model;

import com.example.logandrechmvp.bean.LogBean;
import com.example.logandrechmvp.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/6.
 */

public interface IShowlogmodel {
     void showlog(String name, String pwd, final OnNetListener<LogBean> onNetListener);
}
