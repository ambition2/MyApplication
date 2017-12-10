package com.example.monisunday.model;

import android.os.Handler;

import com.example.monisunday.bena.RecycykerBean;
import com.example.monisunday.okthhp.OkHttpUtils;
import com.example.monisunday.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/9.
 */

public class ShowDatamodel implements IShowdataModel {
    Handler handler=new Handler();
    public void showdata(String keywords, String page, final OnNetListener<RecycykerBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/searchProducts?keywords="+keywords+"&page="+page+"", new Callback() {
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
                        RecycykerBean recycykerBean = gson.fromJson(string, RecycykerBean.class);
                        onNetListener.onSuccess(recycykerBean);
                    }
                });
            }
        });

    }
}
