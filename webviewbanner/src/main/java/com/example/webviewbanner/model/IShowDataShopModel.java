package com.example.webviewbanner.model;

import com.example.webviewbanner.bean.XiangQingBean;
import com.example.webviewbanner.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/11.
 */

public interface IShowDataShopModel {
     void ShowShop(String pid, final OnNetListener<XiangQingBean> onNetListener);
}
