package com.example.stuuuiodo.precneter;

import com.example.stuuuiodo.bean.RecyclerBean;
import com.example.stuuuiodo.model.IRecyclerModel;
import com.example.stuuuiodo.model.RecyclerModel;
import com.example.stuuuiodo.view.IRecyclerView;

/**
 * Created by lenovo on 2017/11/22.
 */

public class RecyclerPrecenter {
    RecyclerModel model;
    IRecyclerView view;
    public RecyclerPrecenter(IRecyclerView view){
        this.view=view;
        model=new RecyclerModel();
    }

    public void getdata(int page){
        model.getdata(page,new IRecyclerModel() {
            @Override
            public void success(RecyclerBean bean) {
                view.success(bean);
            }

            @Override
            public void error(Exception e) {
                 view.error(e);
            }
        });
    }


}
