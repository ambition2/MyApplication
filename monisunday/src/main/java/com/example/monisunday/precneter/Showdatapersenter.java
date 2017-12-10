package com.example.monisunday.precneter;

import android.widget.ThemedSpinnerAdapter;

import com.example.monisunday.bena.RecycykerBean;
import com.example.monisunday.model.IShowdataModel;
import com.example.monisunday.model.ShowDatamodel;
import com.example.monisunday.okthhp.OnNetListener;
import com.example.monisunday.view.IShowdataView;

/**
 * Created by lenovo on 2017/12/9.
 */

public class Showdatapersenter {
      IShowdataModel model;
    IShowdataView view;
    public Showdatapersenter(IShowdataView view){
        this.view=view;
        model=new ShowDatamodel();
    }
    public void showdata(String keywords,String page){
        model.showdata(keywords, page, new OnNetListener<RecycykerBean>() {
            @Override
            public void onSuccess(RecycykerBean recycykerBean) {
                 view.showdatare(recycykerBean.getData());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
