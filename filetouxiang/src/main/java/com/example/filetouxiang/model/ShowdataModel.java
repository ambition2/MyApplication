package com.example.filetouxiang.model;

import android.os.Handler;

import com.example.filetouxiang.bean.PeopleBean;
import com.example.filetouxiang.okthhp.OkHttpUtils;
import com.example.filetouxiang.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/10.
 */

public class ShowdataModel implements IshowDataModel {
    Handler handler=new Handler();
    public void ShowData(final OnNetListener<PeopleBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("https://www.zhaoapi.cn/user/getUserInfo?uid=71", new Callback() {
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
                        PeopleBean peopleBean = gson.fromJson(string, PeopleBean.class);
                        onNetListener.onSuccess(peopleBean);
                    }
                });

            }
        });

    }
}
