package com.example.studyone.model;

import com.example.studyone.bean.OneDataBean;

/**
 * Created by lenovo on 2017/11/28.
 */

public interface IShowModel {
       public  void success(OneDataBean bean);
    public  void error(Exception e);
}
