package com.example.logandrechmvp.precenter;

import android.content.Context;
import android.widget.Toast;

import com.example.logandrechmvp.bean.LogBean;
import com.example.logandrechmvp.model.IShowlogmodel;
import com.example.logandrechmvp.model.Showlogbean;
import com.example.logandrechmvp.okthhp.OnNetListener;
import com.example.logandrechmvp.view.ILoginView;

/**
 * Created by lenovo on 2017/12/6.
 */

public class LogPercenter {
    Context context;
    IShowlogmodel model;
    ILoginView view;
    public LogPercenter(Context context,ILoginView view){
        this.context=context;
        this.view=view;
        model=new Showlogbean();
    }
    public void Logshow(){
        String name=view.getName();
        String pwd=view.getPwd();
        model.showlog(name, pwd, new OnNetListener<LogBean>() {
            @Override
            public void onSuccess(LogBean logBean) {
                String code = logBean.getCode();
                if(code.equals("0"))
                {
                    Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
