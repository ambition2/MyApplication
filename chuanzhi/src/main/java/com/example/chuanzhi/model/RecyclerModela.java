package com.example.chuanzhi.model;

import com.example.chuanzhi.bean.RecyclerBeanda;
import com.example.chuanzhi.okhttp.AbstractUiCallBack;
import com.example.chuanzhi.okhttp.OkhttpUtils;


/**
 * Created by lenovo on 2017/11/22.
 */

public class RecyclerModela {
    public  void getdata(int page,final IRecyclerModela model){
        OkhttpUtils.getInstance().asy(null, "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page="+page, new  AbstractUiCallBack<RecyclerBeanda>() {


            @Override
            public void success(RecyclerBeanda bean) {
                model.success(bean);
            }

            @Override
            public void failure(Exception e) {
             model.error(e);
            }
        });

    }
}
