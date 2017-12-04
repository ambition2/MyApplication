package com.example.webviewbanner.model;

import android.os.Handler;
import android.view.View;

import com.example.webviewbanner.bean.BannerBean;
import com.example.webviewbanner.bean.Catagorybean;
import com.example.webviewbanner.bean.ProductCatagorybean;
import com.example.webviewbanner.okthhp.OkHttpUtils;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.CookieHandler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/1.
 */

public class Showdatamodel implements IShowdatamodel {
    private Handler handler=new Handler();
    public void getCatagory(final OnNetListener<Catagorybean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/getCatagory", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string= response.body().string();
                       handler.post(new Runnable() {
                           @Override
                           public  void run() {
                               Catagorybean json = new Gson().fromJson(string, Catagorybean.class);
                               onNetListener.onSuccess(json);
                           }
                       });
            }
        });

    }
    public  void getProductCatagory(String cid, final OnNetListener<ProductCatagorybean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/getProductCatagory?cid="+cid, new Callback() {

            private String string;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ProductCatagorybean productCatagorybean = new Gson().fromJson(string, ProductCatagorybean.class);
                        onNetListener.onSuccess(productCatagorybean);
                    }
                });
            }
        });

    }
    public void getbanner(final OnNetListener<BannerBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/ad/getAd", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        BannerBean bannerBean = new Gson().fromJson(string, BannerBean.class);
                        onNetListener.onSuccess(bannerBean);
                    }
                });
            }
        });

    }




}
