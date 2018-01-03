package com.example.filetouxiang.model;

import com.example.filetouxiang.bean.PeopleBean;
import com.example.filetouxiang.okthhp.OnNetListener;

/**
 * Created by lenovo on 2017/12/10.
 */

public interface IshowDataModel {
    public void ShowData(final OnNetListener<PeopleBean> onNetListener);
}
