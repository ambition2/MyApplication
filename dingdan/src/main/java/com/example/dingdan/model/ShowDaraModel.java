package com.example.dingdan.model;

import android.os.Handler;

import com.example.dingdan.bean.DinfdanBean;

import com.example.dingdan.okhttp.OkHttpUtils;
import com.example.dingdan.okhttp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/19.
 */

public class ShowDaraModel implements IShowDataModel {
    Handler handler=new Handler();
    public void  showdata(Map<String,String> pams, final OnNetListener<DinfdanBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doPost("http://120.27.23.105/product/getOrders", pams, new Callback() {
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
                     DinfdanBean dingbean = gson.fromJson(string, DinfdanBean.class);
                     onNetListener.onSuccess(dingbean);
                 }
             });
            }
        });
    }
}
