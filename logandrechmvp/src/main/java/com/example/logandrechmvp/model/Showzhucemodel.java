package com.example.logandrechmvp.model;

import android.os.Handler;

import com.example.logandrechmvp.bean.RegBean;
import com.example.logandrechmvp.okthhp.OkHttpUtils;
import com.example.logandrechmvp.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/6.
 */

public class Showzhucemodel implements IShowzhuceModel {
    Handler handler=new Handler();

    public  void showzhuce(String name, String pwd, final OnNetListener<RegBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/user/reg?mobile=" + name + "&password=" + pwd, new Callback() {
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
                        RegBean regBean = gson.fromJson(string, RegBean.class);
                        onNetListener.onSuccess(regBean);
                    }
                });
            }
        });

    }
}
