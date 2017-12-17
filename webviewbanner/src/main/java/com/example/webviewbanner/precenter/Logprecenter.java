package com.example.webviewbanner.precenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.webviewbanner.activity.LogActivity;
import com.example.webviewbanner.activity.MainActivity;
import com.example.webviewbanner.bean.LogBean;
import com.example.webviewbanner.model.IShowLogModel;
import com.example.webviewbanner.model.ShowLogModel;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.example.webviewbanner.view.IShowlogVew;


/**
 * Created by lenovo on 2017/12/11.
 */

public class Logprecenter {
    Context context;
    IShowLogModel model;
    IShowlogVew view;
    public Logprecenter(Context context, IShowlogVew view){
        this.context=context;
        this.view=view;
        model=new ShowLogModel();
        /**
         * 15613082890
         * 123456
         */
    }
    public  void showdata(){
        String name=view.getName();
        String pwd=view.getPwd();
        model.showlogdata(name, pwd, new OnNetListener<LogBean>() {
            @Override
            public void onSuccess(LogBean logBean) {
                view.success(logBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }



}
