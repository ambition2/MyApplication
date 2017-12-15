package com.example.appgouwucar.precener;

import com.example.appgouwucar.bean.XiangqingBean;
import com.example.appgouwucar.model.IShowXiangModel;
import com.example.appgouwucar.model.ShowXiangModel;
import com.example.appgouwucar.okthhp.OnNetListener;
import com.example.appgouwucar.view.IShowXiangView;
import com.example.appgouwucar.view.IShowlogVew;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/13.
 */

public class XiangPrecneter {
    IShowXiangModel model;
    IShowXiangView view;
    public  XiangPrecneter(IShowXiangView view){
        this.view=view;
        model=new ShowXiangModel();
    }
    public void showdata(String pid){
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        map.put("source","android");

        model.showxiangdata(map, new OnNetListener<XiangqingBean>() {
            @Override
            public void onSuccess(XiangqingBean bean) {

                view.showdata(bean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
