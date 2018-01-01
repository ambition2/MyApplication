package com.example.showyeduotioamu.model;

import com.example.showyeduotioamu.net.IShowNetUrl;
import com.example.showyeduotioamu.net.RetrofitManager;

import okhttp3.OkHttpClient;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/30.
 */

public class ShowyeModel implements IShowyeModel {
    @Override
    public void showdata(Observer observer) {
        OkHttpClient okk=new OkHttpClient.Builder().build();
        RetrofitManager.getInstance(IShowNetUrl.BASE1_URL,okk)
                .setCreate(IShowNetUrl.class)
                .getbean("homepage")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
