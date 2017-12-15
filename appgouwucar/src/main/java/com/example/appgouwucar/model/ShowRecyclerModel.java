package com.example.appgouwucar.model;

import android.os.Handler;


import com.example.appgouwucar.bean.RecyclerBean;
import com.example.appgouwucar.okthhp.OkHttpUtils;
import com.example.appgouwucar.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/11.
 */

public class ShowRecyclerModel implements  IShowRecyclerModel{
    Handler handler=new Handler();
    public void ShowRecyclerData(String pscid,String page,final OnNetListener<RecyclerBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/getProducts?pscid="+pscid+"", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      Gson gson=new Gson();
                        RecyclerBean recyclerBean = gson.fromJson(string, RecyclerBean.class);
                        onNetListener.onSuccess(recyclerBean);

                    }
                });
            }
        });

    }
}
