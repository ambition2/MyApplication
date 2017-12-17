package com.example.webviewbanner.model;

import android.os.Handler;

import com.example.webviewbanner.bean.XiangQingBean;
import com.example.webviewbanner.okthhp.OkHttpUtils;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/11.
 */

public class ShowDataShopModel implements IShowDataShopModel {
    Handler handler=new Handler();
    public  void ShowShop(String pid, final OnNetListener<XiangQingBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/getProductDetail?pid=" + pid + "", new Callback() {
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
                        XiangQingBean xiangQingBean = gson.fromJson(string, XiangQingBean.class);
                        onNetListener.onSuccess(xiangQingBean);
                    }
                });
            }
        });

    }
}
