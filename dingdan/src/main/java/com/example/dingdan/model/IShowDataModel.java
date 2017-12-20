package com.example.dingdan.model;

import com.example.dingdan.bean.DinfdanBean;
import com.example.dingdan.okhttp.OnNetListener;


import java.util.Map;

/**
 * Created by lenovo on 2017/12/19.
 */

public interface IShowDataModel {
    public void  showdata(Map<String, String> pams, final OnNetListener<DinfdanBean> onNetListener);
}
