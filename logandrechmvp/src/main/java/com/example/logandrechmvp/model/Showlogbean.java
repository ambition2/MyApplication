package com.example.logandrechmvp.model;

import android.os.Handler;

import com.example.logandrechmvp.bean.LogBean;
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

public class Showlogbean  implements IShowlogmodel  {

    Handler handler=new Handler();
    public void showlog(String name, String pwd, final OnNetListener<LogBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/user/login?mobile=" + name + "&password=" + pwd, new Callback() {
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
                        LogBean logBean = gson.fromJson(string, LogBean.class);
                        onNetListener.onSuccess(logBean);
                    }
                });
            }
        });

    }

}
