package com.example.appgouwucar.view;

import com.example.appgouwucar.bean.LogBean;

/**
 * Created by lenovo on 2017/12/11.
 */

public interface IShowlogVew {
    void setName(String name);
    String getName();
    void setpPwd(String pwd);
    String getPwd();
    void success(LogBean bean);
}
