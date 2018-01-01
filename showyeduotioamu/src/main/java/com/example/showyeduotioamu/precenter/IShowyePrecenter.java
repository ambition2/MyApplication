package com.example.showyeduotioamu.precenter;

/**
 * Created by lenovo on 2017/12/30.
 */

public interface IShowyePrecenter<T> {
    //绑定
    void attach(T view);
    //解绑
    void detach();
}
