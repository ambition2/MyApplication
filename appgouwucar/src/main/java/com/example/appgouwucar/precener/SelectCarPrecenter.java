package com.example.appgouwucar.precener;

import com.example.appgouwucar.bean.SelectCarBean;
import com.example.appgouwucar.model.IShowSelectCarModel;
import com.example.appgouwucar.model.ShowSelectCarBean;
import com.example.appgouwucar.okthhp.OnNetListener;
import com.example.appgouwucar.view.IShowAddcarView;
import com.example.appgouwucar.view.IShowSelectCarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/13.
 */

public class SelectCarPrecenter {
    IShowSelectCarModel model;
    IShowSelectCarView view;
    public  SelectCarPrecenter(IShowSelectCarView view){
        this.view=view;
        model=new ShowSelectCarBean();
    }
    public  void selectcar(String uid){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("source","android");
        model.showselectcar(map, new OnNetListener<SelectCarBean>() {
            @Override
            public void onSuccess(SelectCarBean selectCarBean) {
                List<List<SelectCarBean.DataBean.ListBean>> child=new ArrayList<List<SelectCarBean.DataBean.ListBean>>();
                for (int i=0;i<selectCarBean.getData().size();i++){
                    child.add(selectCarBean.getData().get(i).getList());
                }
                view.showselectcar(selectCarBean.getData(),child);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
