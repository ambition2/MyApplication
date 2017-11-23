package com.example.chuanzhi.precneter;

import com.example.chuanzhi.bean.RecyclerBeanda;
import com.example.chuanzhi.model.IRecyclerModela;
import com.example.chuanzhi.model.RecyclerModela;
import com.example.chuanzhi.view.IRecyclerViewa;


/**
 * Created by lenovo on 2017/11/22.
 */

public class RecyclerPrecenter {
    RecyclerModela model;
    IRecyclerViewa view;
    public RecyclerPrecenter(IRecyclerViewa view){
        this.view=view;
        model=new RecyclerModela();
    }

    public void getdata(int page){
        model.getdata(page,new IRecyclerModela() {
            @Override
            public void success(RecyclerBeanda bean) {
                view.success(bean);
            }

            @Override
            public void error(Exception e) {
                 view.error(e);
            }
        });
    }


}
