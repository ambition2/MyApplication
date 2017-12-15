package com.example.appgouwucar.precener;

import com.example.appgouwucar.bean.AddcarBean;
import com.example.appgouwucar.model.IShowAddCarModel;
import com.example.appgouwucar.model.ShowAddCarModel;
import com.example.appgouwucar.okthhp.OnNetListener;
import com.example.appgouwucar.view.IShowAddcarView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/13.
 */

public class AddCarPrecenter {
    IShowAddCarModel model;
    IShowAddcarView view;
    public AddCarPrecenter(IShowAddcarView view){
        this.view=view;
        model=new ShowAddCarModel();
    }
    public  void showaddcar(String uid,String pid){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        map.put("source","android");
        model.showaddcar(map, new OnNetListener<AddcarBean>() {
            @Override
            public void onSuccess(AddcarBean addcarBean) {
                view.showcar(addcarBean.getMsg());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
