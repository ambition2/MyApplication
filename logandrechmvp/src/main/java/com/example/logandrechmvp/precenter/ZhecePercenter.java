package com.example.logandrechmvp.precenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.logandrechmvp.MainActivity;
import com.example.logandrechmvp.bean.RegBean;
import com.example.logandrechmvp.model.IShowlogmodel;
import com.example.logandrechmvp.model.IShowzhuceModel;
import com.example.logandrechmvp.model.Showlogbean;
import com.example.logandrechmvp.model.Showzhucemodel;
import com.example.logandrechmvp.okthhp.OnNetListener;
import com.example.logandrechmvp.view.ILoginView;
import com.example.logandrechmvp.view.IRegView;

/**
 * Created by lenovo on 2017/12/6.
 */

public class ZhecePercenter {
    Context context;
    IShowzhuceModel model;
    IRegView view;
    public ZhecePercenter(Context context,IRegView view){
        this.context=context;
        this.view=view;
        model=new Showzhucemodel();
    }
    public void showzhecu(){
        String name = view.getName();
        String pwd = view.getPwd();
        model.showzhuce(name, pwd, new OnNetListener<RegBean>() {
            @Override
            public void onSuccess(RegBean regBean) {
                String code = regBean.getCode();
                if(code.equals("0"))
                {
                    Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }else
                {
                    Toast.makeText(context,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
