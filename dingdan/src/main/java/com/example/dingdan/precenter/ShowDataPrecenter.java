package com.example.dingdan.precenter;



import com.example.dingdan.bean.DinfdanBean;
import com.example.dingdan.model.IShowDataModel;
import com.example.dingdan.model.ShowDaraModel;
import com.example.dingdan.okhttp.OnNetListener;
import com.example.dingdan.view.IShowDataView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/19.
 */

public class ShowDataPrecenter {
    ShowDaraModel model;
    IShowDataView view;
    public ShowDataPrecenter(IShowDataView view){
        this.view=view;
        model=new ShowDaraModel();
    }
    public void showlastdata(String uid,String page){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("page",page);

        model.showdata(map, new OnNetListener<DinfdanBean>() {
            @Override
            public void onSuccess(DinfdanBean dingbean) {
                view.showdata(dingbean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
