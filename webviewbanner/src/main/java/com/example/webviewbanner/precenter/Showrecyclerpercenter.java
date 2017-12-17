package com.example.webviewbanner.precenter;

import com.example.webviewbanner.bean.RecyclerBean;
import com.example.webviewbanner.model.IShowrecycler;
import com.example.webviewbanner.model.Showrecycler;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.example.webviewbanner.view.IShowrecyclerView;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by lenovo on 2017/12/5.
 */

public class Showrecyclerpercenter {
    IShowrecycler model;
    IShowrecyclerView view;
    public Showrecyclerpercenter(IShowrecyclerView view){
        this.view=view;
        model=new Showrecycler();
    }
    public void showrecycler(String pid){
         model.getrecycler(pid,new OnNetListener<RecyclerBean>() {
             @Override
             public void onSuccess(RecyclerBean recyclerBean) {

                 List<RecyclerBean.DataBean> data = recyclerBean.getData();
                 view.showrecycler(data);
             }

             @Override
             public void onFailure(Exception e) {

             }
         });
    }
}
