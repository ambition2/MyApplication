package com.example.stuuuiodo.view;

import com.example.stuuuiodo.bean.RecyclerBean;

/**
 * Created by lenovo on 2017/11/22.
 */

public interface IRecyclerView {
    public  void success(RecyclerBean bean);
    public  void  error(Exception e);
}
