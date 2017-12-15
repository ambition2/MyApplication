package com.example.appgouwucar.precener;

import android.content.Context;

import com.example.appgouwucar.bean.RecyclerBean;
import com.example.appgouwucar.model.IShowRecyclerModel;
import com.example.appgouwucar.model.ShowRecyclerModel;
import com.example.appgouwucar.okthhp.OnNetListener;
import com.example.appgouwucar.view.IShowRecyclerView;


/**
 * Created by lenovo on 2017/12/11.
 */

public class Recyclerpersenter {
    Context context;
    IShowRecyclerModel model;
    IShowRecyclerView view;
    public  Recyclerpersenter(Context context,IShowRecyclerView view){
        this.context=context;
        this.view=view;
        model=new ShowRecyclerModel();
    }
    public  void showdata(String pscid,String page){
        model.ShowRecyclerData(pscid,page, new OnNetListener<RecyclerBean>() {
            @Override
            public void onSuccess(RecyclerBean recyclerBean) {
                 view.showdata(recyclerBean.getData());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
