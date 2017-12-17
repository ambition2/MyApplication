package com.example.webviewbanner.model;

import android.os.Handler;

import com.example.webviewbanner.bean.RecyclerBean;
import com.example.webviewbanner.okthhp.OkHttpUtils;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/12/5.
 */

public class Showrecycler implements IShowrecycler {
    Handler handler=new Handler();
    public void getrecycler(String pscid,final OnNetListener<RecyclerBean> onNetListener){
        OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/getProducts?pscid="+pscid+"", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                  @Override
                  public void run() {

                      RecyclerBean recyclerBean = new Gson().fromJson(string, RecyclerBean.class);
                      onNetListener.onSuccess(recyclerBean);
                  }
              });


            }
        });
    }
}
