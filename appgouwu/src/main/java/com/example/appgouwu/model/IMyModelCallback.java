package com.example.appgouwu.model;

import com.example.appgouwu.bean.ShopBean;

/**
 * Created by lenovo on 2017/11/21.
 */

public interface IMyModelCallback {
    public void success(ShopBean bean);
    public  void error(Exception e);
}
