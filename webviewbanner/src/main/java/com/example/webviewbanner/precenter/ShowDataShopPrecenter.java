package com.example.webviewbanner.precenter;

import com.example.webviewbanner.bean.XiangQingBean;
import com.example.webviewbanner.model.IShowDataShopModel;
import com.example.webviewbanner.model.ShowDataShopModel;
import com.example.webviewbanner.okthhp.OnNetListener;
import com.example.webviewbanner.view.IShowDataShopView;

/**
 * Created by lenovo on 2017/12/11.
 */

public class ShowDataShopPrecenter {
    IShowDataShopModel model;
    IShowDataShopView view;
    public ShowDataShopPrecenter(IShowDataShopView view){
        this.view=view;
        model=new ShowDataShopModel();
    }
    public  void showdata(String pid){
        model.ShowShop(pid, new OnNetListener<XiangQingBean>() {
            @Override
            public void onSuccess(XiangQingBean bean) {
                view.showshopdata(bean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
