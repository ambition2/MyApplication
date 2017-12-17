package com.example.webviewbanner.view;

import com.example.webviewbanner.bean.BannerBean;
import com.example.webviewbanner.bean.Catagorybean;
import com.example.webviewbanner.bean.ProductCatagorybean;
import com.example.webviewbanner.bean.RecyclerBean;

import java.util.List;

/**
 * Created by lenovo on 2017/12/1.
 */

public interface IShowdataview {
    public void showCatagory(List<Catagorybean.DataBean> list);
    public void showProductCatagory(List<ProductCatagorybean.DataBean> group, List<List<ProductCatagorybean.DataBean.ListBean>> child);
    public  void showbanner(List<BannerBean.DataBean> data);


}
