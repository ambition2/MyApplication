package com.example.logandrechmvp.model;

import com.example.logandrechmvp.bean.RegBean;
import com.example.logandrechmvp.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/6.
 */

public interface IShowzhuceModel {
      void showzhuce(String name, String pwd, final OnNetListener<RegBean> onNetListener);
}
