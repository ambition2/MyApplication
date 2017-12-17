package com.example.webviewbanner.model;

import com.example.webviewbanner.bean.BannerBean;
import com.example.webviewbanner.bean.Catagorybean;
import com.example.webviewbanner.bean.ProductCatagorybean;
import com.example.webviewbanner.bean.RecyclerBean;
import com.example.webviewbanner.okthhp.OnNetListener;

import okhttp3.Callback;

/**
 * Created by lenovo on 2017/12/1.
 */

public interface IShowdatamodel {
    public void getCatagory( OnNetListener<Catagorybean> onNetListener);
    public  void getProductCatagory(String cid, OnNetListener<ProductCatagorybean> onNetListener);
    public void getbanner( OnNetListener<BannerBean> onNetListener);


}
