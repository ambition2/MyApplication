package com.example.chenyunfei20171129.model;

import com.example.chenyunfei20171129.bean.TwoDataBean;
import com.example.chenyunfei20171129.okhttp.AbstractUiCallBack;
import com.example.chenyunfei20171129.okhttp.OkhttpUtils;

/**
 * Created by lenovo on 2017/11/29.
 */

public class Showdatamodel {
    public  void getdata(int page, final IShowdatamodel model){
        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getProducts?pscid=39&page=" + page, new AbstractUiCallBack<TwoDataBean>() {


            @Override
            public void success(TwoDataBean bean) {
                   model.success(bean);
            }

            @Override
            public void failure(Exception e) {
                model.error(e);
            }
        });

    }
}
