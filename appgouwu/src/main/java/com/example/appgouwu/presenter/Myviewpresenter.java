package com.example.appgouwu.presenter;

import com.example.appgouwu.bean.ShopBean;
import com.example.appgouwu.model.IMyModelCallback;
import com.example.appgouwu.model.MyMolder;
import com.example.appgouwu.view.Mainview;

/**
 * Created by lenovo on 2017/11/21.
 */

public class Myviewpresenter {

  private  Mainview listener;
    private MyMolder molder;
    public  Myviewpresenter(Mainview listener){
       this.listener=listener;
        this.molder=new MyMolder();

    }
    public  void getdata(){
        molder.getdata(new IMyModelCallback() {

            @Override
            public void success(ShopBean bean) {
                if(listener!=null)
                {
                    listener.success(bean);
                }

            }

            @Override
            public void error(Exception e) {
                if(listener!=null)
                {
                    listener.error(e);
                }
            }
        });

    }
    public  void Destory(){
        this.listener=null;
    }
}
