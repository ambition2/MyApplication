package com.example.webviewbanner.precenter;

import android.util.Log;

import com.example.webviewbanner.bean.BannerBean;
import com.example.webviewbanner.bean.Catagorybean;
import com.example.webviewbanner.bean.ProductCatagorybean;
import com.example.webviewbanner.bean.RecyclerBean;
import com.example.webviewbanner.model.IShowdatamodel;
import com.example.webviewbanner.model.Showdatamodel;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.example.webviewbanner.view.IShowdataview;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/1.
 */

public class ShowdataPrecenter {
    IShowdatamodel model;
    IShowdataview  view;

    public  ShowdataPrecenter(IShowdataview view){
        this.view=view;
        model=new Showdatamodel();


    }
    public  void getCatagory(){
        model.getCatagory(new OnNetListener<Catagorybean>() {
            @Override
            public void onSuccess(Catagorybean catagorybean) {

                view.showCatagory(catagorybean.getData());
                getproduct(catagorybean.getData().get(0).getCid()+"");

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    public void  getproduct(String cid){

        model.getProductCatagory(cid, new OnNetListener<ProductCatagorybean>() {
            @Override
            public void onSuccess(ProductCatagorybean productCatagorybean) {
                List<List<ProductCatagorybean.DataBean.ListBean>> child = new ArrayList<>();
                List<ProductCatagorybean.DataBean> group = productCatagorybean.getData();
                for (int i = 0; i < group.size(); i++) {
                    ProductCatagorybean.DataBean dataBean = group.get(i);
                    List<ProductCatagorybean.DataBean.ListBean> list = dataBean.getList();
                    child.add(list);
                }
                view.showProductCatagory(group, child);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    public  void getbean(){
        model.getbanner(new OnNetListener<BannerBean>() {
            @Override
            public void onSuccess(BannerBean bannerBean) {
                List<BannerBean.DataBean> data = bannerBean.getData();
                view.showbanner(data);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}
