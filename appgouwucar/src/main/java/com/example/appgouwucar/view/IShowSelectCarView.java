package com.example.appgouwucar.view;

import com.example.appgouwucar.bean.SelectCarBean;

import java.util.List;

/**
 * Created by lenovo on 2017/12/13.
 */

public interface IShowSelectCarView {
    void showselectcar(List<SelectCarBean.DataBean> group,List<List<SelectCarBean.DataBean.ListBean>> child);
}
