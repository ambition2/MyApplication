package com.example.chenyunfei20171129.model;

import com.example.chenyunfei20171129.bean.TwoDataBean;

/**
 * Created by lenovo on 2017/11/29.
 */

public interface IShowdatamodel {
     public  void success(TwoDataBean bean);
    public   void error(Exception e);
}
