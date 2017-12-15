package com.example.appgouwucar.model;

import android.os.Handler;

import com.example.appgouwucar.bean.SelectCarBean;
import com.example.appgouwucar.okthhp.OkHttpUtils;
import com.example.appgouwucar.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/13.
 */

public class ShowSelectCarBean implements IShowSelectCarModel {
    Handler handler=new Handler();
    @Override
    public void showselectcar(Map<String, String> pas, final OnNetListener<SelectCarBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost("http://120.27.23.105/product/getCarts", pas, new Callback() {
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
                         SelectCarBean selectCarBean = gson.fromJson(string, SelectCarBean.class);
                         onNetListener.onSuccess(selectCarBean);
                     }
                 });
            }
        });
    }
}
