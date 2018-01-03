package com.example.filetouxiang.presenter;

import com.example.filetouxiang.bean.PeopleBean;
import com.example.filetouxiang.model.IshowDataModel;
import com.example.filetouxiang.model.ShowdataModel;
import com.example.filetouxiang.okthhp.OnNetListener;
import com.example.filetouxiang.view.IShowDataView;

/**
 * Created by lenovo on 2017/12/10.
 */

public class ShowPercneter {
    IshowDataModel model;
    IShowDataView view;
    public ShowPercneter(IShowDataView view){
        this.view=view;
        model=new ShowdataModel();
    }
    public void showdata(){
        model.ShowData(new OnNetListener<PeopleBean>() {
            @Override
            public void onSuccess(PeopleBean bean) {
                view.shiwdata(bean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
