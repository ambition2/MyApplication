package com.example.appgouwu.model;

import com.example.appgouwu.bean.ShopBean;
import com.example.appgouwu.okhttp.AbstractUiCallBack;
import com.example.appgouwu.okhttp.OkhttpUtils;

/**
 * Created by lenovo on 2017/11/21.
 */

public class MyMolder {

    public void getdata(final IMyModelCallback callback){
        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getCarts?uid=100", new AbstractUiCallBack<ShopBean>() {
            @Override
            public void success(ShopBean bean) {
                  callback.success(bean);
            }

            @Override
            public void failure(Exception e) {
                     callback.error(e);
            }
        });
    };
}
