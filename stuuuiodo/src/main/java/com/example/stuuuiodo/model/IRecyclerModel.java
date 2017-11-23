package com.example.stuuuiodo.model;

import com.example.stuuuiodo.bean.RecyclerBean;

/**
 * Created by lenovo on 2017/11/22.
 */

public interface IRecyclerModel {
     public  void success(RecyclerBean bean);
    public  void  error(Exception e);
}
