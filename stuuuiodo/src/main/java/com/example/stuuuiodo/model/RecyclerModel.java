package com.example.stuuuiodo.model;

import com.example.stuuuiodo.bean.RecyclerBean;
import com.example.stuuuiodo.okhttp.AbstractUiCallBack;
import com.example.stuuuiodo.okhttp.OkhttpUtils;

import okhttp3.OkHttpClient;

/**
 * Created by lenovo on 2017/11/22.
 */

public class RecyclerModel {
    public  void getdata(int page,final IRecyclerModel model){
        OkhttpUtils.getInstance().asy(null, "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page="+page, new AbstractUiCallBack<RecyclerBean>() {


            @Override
            public void success(RecyclerBean bean) {
                model.success(bean);
            }

            @Override
            public void failure(Exception e) {
             model.error(e);
            }
        });

    }
}
