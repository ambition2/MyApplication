package com.example.webviewbanner.model;

import android.os.Handler;

import com.example.webviewbanner.bean.AddCarBean;
import com.example.webviewbanner.okthhp.OkHttpUtils;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/12.
 */

public class ShowAddCarModel implements  IShowAddCarModel {
    Handler handler=new Handler();
    @Override
    public void ShowAddCar(Map<String, String> parms, final OnNetListener<AddCarBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost("http://120.27.23.105/product/addCart&source=android", parms, new Callback() {
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
                            AddCarBean addCarBean = gson.fromJson(string, AddCarBean.class);
                            onNetListener.onSuccess(addCarBean);
                        }
                    });
            }
        });
    }
}
