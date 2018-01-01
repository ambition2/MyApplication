
package com.example.showyeduotioamu.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.showyeduotioamu.precenter.IShowyePrecenter;
import com.example.showyeduotioamu.precenter.ShowyePrecenter;

import retrofit2.http.POST;

/**
 * Created by lenovo on 2017/12/30.
 */

public abstract class BaseFragment<T extends IShowyePrecenter> extends Fragment {
    T precenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        creatprecenter();
    }
    protected  abstract void creatprecenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(precenter!=null)
        {
            precenter.detach();
        }
    }
}
