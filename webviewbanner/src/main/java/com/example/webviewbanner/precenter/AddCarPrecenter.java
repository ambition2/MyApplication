package com.example.webviewbanner.precenter;

import com.example.webviewbanner.bean.AddCarBean;
import com.example.webviewbanner.model.IShowAddCarModel;
import com.example.webviewbanner.model.ShowAddCarModel;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.example.webviewbanner.view.IShowAddCarView;
import com.example.webviewbanner.view.IShowDataShopView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/12.
 */

public class AddCarPrecenter {
    IShowAddCarModel model;
    IShowAddCarView view;
    public AddCarPrecenter(IShowAddCarView view){
         this.view=view;
        model=new ShowAddCarModel();
    }
    public void showaddcar(String uid,String pid){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);

         model.ShowAddCar(map, new OnNetListener<AddCarBean>() {
             @Override
             public void onSuccess(AddCarBean addCarBean) {
                   view.showdata(addCarBean.getMsg());
             }

             @Override
             public void onFailure(Exception e) {

             }
         });
    }

}
