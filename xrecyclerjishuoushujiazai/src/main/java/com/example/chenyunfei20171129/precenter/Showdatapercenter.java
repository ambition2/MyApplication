package com.example.chenyunfei20171129.precenter;

import com.example.chenyunfei20171129.bean.TwoDataBean;
import com.example.chenyunfei20171129.model.IShowdatamodel;
import com.example.chenyunfei20171129.model.Showdatamodel;
import com.example.chenyunfei20171129.view.IShowdataview;

/**
 * Created by lenovo on 2017/11/29.
 */

public class Showdatapercenter {
    IShowdataview  view;
    Showdatamodel model;
    public  Showdatapercenter(IShowdataview view){
        this.view=view;
        model=new Showdatamodel();
    }
    public  void getdtaa(int page){

        model.getdata(page, new IShowdatamodel() {
            @Override
            public void success(TwoDataBean bean) {
                view.success(bean);
            }

            @Override
            public void error(Exception e) {
              view.error(e);
            }
        });
    }
}
